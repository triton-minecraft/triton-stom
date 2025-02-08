package dev.kyriji.tritonstom.world;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.event.player.PlayerBlockInteractEvent;
import net.minestom.server.event.player.PlayerBlockPlaceEvent;
import net.minestom.server.instance.Instance;

public class WorldListener {
	private static WorldListener INSTANCE;

	private WorldListener() {
		MinecraftServer.getGlobalEventHandler().addListener(PlayerBlockBreakEvent.class, event -> {
			Instance instance = event.getPlayer().getInstance();
			WorldManager.get().getWorlds().forEach(tritonWorld -> {
				if(tritonWorld.getInstance() == instance && !tritonWorld.getAllowModification()) event.setCancelled(true);
			});
		});

		MinecraftServer.getGlobalEventHandler().addListener(PlayerBlockInteractEvent.class, event -> {
			Instance instance = event.getPlayer().getInstance();
			WorldManager.get().getWorlds().forEach(tritonWorld -> {
				if(tritonWorld.getInstance() == instance && !tritonWorld.getAllowModification()) event.setCancelled(true);
			});
		});

		MinecraftServer.getGlobalEventHandler().addListener(PlayerBlockPlaceEvent.class, event -> {
			Instance instance = event.getPlayer().getInstance();
			WorldManager.get().getWorlds().forEach(tritonWorld -> {
				if(tritonWorld.getInstance() == instance && !tritonWorld.getAllowModification()) event.setCancelled(true);
			});
		});
	}

	public static void init() {
		if (INSTANCE != null) throw new IllegalStateException();
		INSTANCE = new WorldListener();
	}

	public static WorldListener get() {
		return INSTANCE;
	}
}
