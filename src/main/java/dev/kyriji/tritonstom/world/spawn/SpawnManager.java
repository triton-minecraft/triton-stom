package dev.kyriji.tritonstom.world.spawn;

import dev.kyriji.tritonstom.TritonStom;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;

public class SpawnManager {
	private SpawnManager() {
		MinecraftServer.getGlobalEventHandler().addListener(AsyncPlayerConfigurationEvent.class, event -> {
			Player player = event.getPlayer();
			SpawnLocation spawnLocation = TritonStom.get().getPlayerSpawner().getSpawnProvider().getSpawnLocation(event);
			event.setSpawningInstance(spawnLocation.world().getInstance());
			player.setRespawnPoint(spawnLocation.position());
		});
	}

	public PlayerSpawner.Builder buildPlayerSpawner() {
		return new PlayerSpawner.Builder();
	}

	private static final class InstanceHolder {
		public static final SpawnManager INSTANCE = new SpawnManager();
	}

	public static SpawnManager get() {
		return InstanceHolder.INSTANCE;
	}
}
