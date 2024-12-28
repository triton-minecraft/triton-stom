package dev.kyriji.tritonstom.worlds;

import dev.kyriji.tritonstom.worlds.time.TimeKeeper;
import dev.kyriji.tritonstom.worlds.time.TimeManager;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.anvil.AnvilLoader;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.generator.Generator;

public class TritonWorld {
	private final InstanceContainer instance;
	private final String name;

	private TimeKeeper timeKeeper;

	private TritonWorld(InstanceContainer instance, String name) {
		this.instance = instance;
		this.name = name;
	}

	public InstanceContainer getInstance() {
		return instance;
	}

	public String getName() {
		return name;
	}

	public TimeKeeper getTimeKeeper() {
		return timeKeeper;
	}

	public static class Builder {
		private final String name;

		private boolean enableLighting = true;
		private Generator generator = unit -> unit.modifier().fillHeight(0, 1, Block.SANDSTONE);

		private TimeKeeper.Builder timeBuilder = TimeManager.get().buildTimeKeeper();

		Builder(String name) {
			this.name = name;
		}

		public Builder lighting(boolean enableLighting) {
			this.enableLighting = enableLighting;
			return this;
		}

		public Builder generator(Generator generator) {
			this.generator = generator;
			return this;
		}

		public Builder timeKeeper(TimeKeeper.Builder timeBuilder) {
			this.timeBuilder = timeBuilder;
			return this;
		}

		public TritonWorld build() {
			InstanceManager instanceManager = MinecraftServer.getInstanceManager();
			InstanceContainer instance = instanceManager.createInstanceContainer();

			instance.setChunkLoader(new AnvilLoader("data/worlds/" + name));
			if (enableLighting) instance.setChunkSupplier(LightingChunk::new);
			instance.setGenerator(generator);

			TritonWorld world = new TritonWorld(instance, name);
			world.timeKeeper = timeBuilder.build(world);
			WorldManager.get().addWorld(world);
			return world;
		}
	}
}
