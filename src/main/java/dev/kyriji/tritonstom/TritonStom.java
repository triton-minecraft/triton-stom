package dev.kyriji.tritonstom;

import dev.kyriji.tritonstom.worlds.spawn.PlayerSpawner;
import dev.kyriji.tritonstom.worlds.spawn.SpawnManager;
import net.minestom.server.MinecraftServer;

import java.util.function.Consumer;

public class TritonStom {
	private static TritonStom INSTANCE;

	final MinecraftServer server;

	private PlayerSpawner playerSpawner;

	private TritonStom(MinecraftServer server) {
		this.server = server;
	}

	public PlayerSpawner getPlayerSpawner() {
		return playerSpawner;
	}

	public static Builder builder(MinecraftServer server) {
		return new Builder(server);
	}

	public static TritonStom get() {
		return INSTANCE;
	}

	public static boolean isInitialized() {
		return INSTANCE != null;
	}

	public static void checkInitialized() {
		if (!isInitialized()) throw new IllegalStateException("TritonStom has not been initialized");
	}

	public static class Builder {
		private final MinecraftServer server;

		private final PlayerSpawner.Builder spawnerBuilder = SpawnManager.get().buildPlayerSpawner();

		Builder(MinecraftServer server) {
			this.server = server;
		}

		public Builder playerSpawner(Consumer<PlayerSpawner.Builder> consumer) {
			consumer.accept(this.spawnerBuilder);
			return this;
		}

		public TritonStom start() {
			if (INSTANCE != null) throw new IllegalStateException("TritonStom has already been initialized");
			INSTANCE = new TritonStom(server);

			INSTANCE.playerSpawner = spawnerBuilder.build();
			return INSTANCE;
		}
	}
}
