package dev.kyriji.tritonstom.worlds.spawn;

public class PlayerSpawner {
	private SpawnLocationProvider spawnProvider;

	private PlayerSpawner() {}

	public SpawnLocationProvider getSpawnProvider() {
		return spawnProvider;
	}

	public static class Builder {
		private SpawnLocationProvider spawnProvider;

		Builder() {}

		public Builder fixed(SpawnLocation fixedSpawnLocation) {
			spawnProvider = event -> fixedSpawnLocation;
			return this;
		}

		public Builder dynamic(SpawnLocationProvider spawnProvider) {
			this.spawnProvider = spawnProvider;
			return this;
		}

		public PlayerSpawner build() {
			PlayerSpawner playerSpawner = new PlayerSpawner();
			playerSpawner.spawnProvider = spawnProvider;
			return playerSpawner;
		}
	}
}
