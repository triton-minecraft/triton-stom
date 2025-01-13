package dev.kyriji.tritonstom;

import dev.kyriji.tritonstom.player.PlayerManager;
import dev.kyriji.tritonstom.world.edit.WorldEditManager;
import dev.kyriji.tritonstom.world.spawn.PlayerSpawner;
import dev.kyriji.tritonstom.world.spawn.SpawnManager;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.GameMode;

public class TritonStom {
	private static TritonStom INSTANCE;

	final MinecraftServer server;

	private GameMode defaultGameMode;
	private PlayerSpawner playerSpawner;

	private TritonStom(MinecraftServer server) {
		this.server = server;
	}

	public GameMode getDefaultGameMode() {
		return defaultGameMode;
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

		private boolean enableWorldEdit = false;
		private GameMode defaultGameMode = GameMode.SURVIVAL;

		private PlayerSpawner.Builder spawnerBuilder = SpawnManager.get().buildPlayerSpawner();

		Builder(MinecraftServer server) {
			this.server = server;
		}

		public Builder enableWorldEdit() {
			this.enableWorldEdit = true;
			return this;
		}

		public Builder defaultGameMode(GameMode defaultGameMode) {
			this.defaultGameMode = defaultGameMode;
			return this;
		}

		public Builder playerSpawner(PlayerSpawner.Builder spawnerBuilder) {
			this.spawnerBuilder = spawnerBuilder;
			return this;
		}

		public TritonStom start() {
			if (INSTANCE != null) throw new IllegalStateException("TritonStom has already been initialized");
			INSTANCE = new TritonStom(server);

			if (enableWorldEdit) WorldEditManager.init();
			INSTANCE.defaultGameMode = defaultGameMode;
			INSTANCE.playerSpawner = spawnerBuilder.build();

			PlayerManager.init();

			return INSTANCE;
		}
	}
}
