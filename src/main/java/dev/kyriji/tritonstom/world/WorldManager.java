package dev.kyriji.tritonstom.world;

import java.util.ArrayList;
import java.util.List;

public class WorldManager {
	private final List<TritonWorld> worlds = new ArrayList<>();

	private WorldManager() {}

	public TritonWorld.Builder buildWorld(String name) {
		return new TritonWorld.Builder(name);
	}

	public List<TritonWorld> getWorlds() {
		return worlds;
	}

	void addWorld(TritonWorld world) {
		worlds.add(world);
	}

	private static final class InstanceHolder {
		public static final WorldManager INSTANCE = new WorldManager();
	}

	public static WorldManager get() {
		return InstanceHolder.INSTANCE;
	}
}
