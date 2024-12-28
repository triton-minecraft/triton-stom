package dev.kyriji.tritonstom.players;

import dev.kyriji.tritonstom.TritonStom;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
	private static PlayerManager INSTANCE;

	public static List<Player> firstSpawnPlayers = new ArrayList<>();

	private PlayerManager() {
		MinecraftServer.getGlobalEventHandler().addListener(PlayerSpawnEvent.class, event -> {
			Player player = event.getPlayer();
			if (firstSpawnPlayers.contains(player)) return;
			firstSpawnPlayers.add(player);
			player.setGameMode(TritonStom.get().getDefaultGameMode());
		});

		MinecraftServer.getGlobalEventHandler().addListener(PlayerDisconnectEvent.class, event -> {
			Player player = event.getPlayer();
			firstSpawnPlayers.remove(player);
		});
	}

	public static void init() {
		if (INSTANCE != null) throw new IllegalStateException();
		INSTANCE = new PlayerManager();
	}

	public static PlayerManager get() {
		return INSTANCE;
	}
}
