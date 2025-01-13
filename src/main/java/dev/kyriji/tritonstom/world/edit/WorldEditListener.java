package dev.kyriji.tritonstom.world.edit;

import dev.kyriji.tritonstom.world.util.Location;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerHand;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.event.player.PlayerBlockInteractEvent;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class WorldEditListener {
	private static WorldEditListener INSTANCE;

	private WorldEditListener() {
		MinecraftServer.getGlobalEventHandler().addListener(PlayerBlockBreakEvent.class, event -> {
			Player player = event.getPlayer();
			ItemStack itemStack = player.getItemInHand(PlayerHand.MAIN);
			if (itemStack.material() != Material.STONE_AXE) return;

			event.setCancelled(true);
			Location location = new Location(player.getInstance(), event.getBlockPosition());
			WorldEditManager.get().selectPosition(player, location, SelectionPosition.FIRST);
		});

		MinecraftServer.getGlobalEventHandler().addListener(PlayerBlockInteractEvent.class, event -> {
			Player player = event.getPlayer();
			ItemStack itemStack = player.getItemInHand(PlayerHand.MAIN);
			if (itemStack.material() != Material.STONE_AXE) return;

			event.setCancelled(true);
			Location location = new Location(player.getInstance(), event.getBlockPosition());
			WorldEditManager.get().selectPosition(player, location, SelectionPosition.SECOND);
		});

		MinecraftServer.getGlobalEventHandler().addListener(PlayerDisconnectEvent.class, event -> {
			Player player = event.getPlayer();
			WorldEditManager.get().getSelectionMap().remove(player);
		});
	}

	public static void init() {
		if (INSTANCE != null) throw new IllegalStateException();
		INSTANCE = new WorldEditListener();
	}

	public static WorldEditListener get() {
		return INSTANCE;
	}
}
