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

import java.util.function.Consumer;

public class TritonWorld {
	private final InstanceContainer instanceContainer;
	private final String name;

	private TimeKeeper timeKeeper;

	private TritonWorld(InstanceContainer container, String name) {
		this.instanceContainer = container;
		this.name = name;
	}

	public InstanceContainer getInstanceContainer() {
		return instanceContainer;
	}

	public String getName() {
		return name;
	}

	public static class Builder {
		private final String name;

		private boolean enableLighting = true;
		private Generator generator = unit -> unit.modifier().fillHeight(0, 1, Block.SANDSTONE);

		private final TimeKeeper.Builder timeBuilder = TimeManager.get().buildTimeKeeper();

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

		public Builder timeKeeper(Consumer<TimeKeeper.Builder> consumer) {
			consumer.accept(this.timeBuilder);
			return this;
		}

		public TritonWorld build() {
			InstanceManager instanceManager = MinecraftServer.getInstanceManager();
			InstanceContainer container = instanceManager.createInstanceContainer();

			container.setChunkLoader(new AnvilLoader("data/worlds/" + name));
			if (enableLighting) container.setChunkSupplier(LightingChunk::new);
			container.setGenerator(generator);

			TritonWorld world = new TritonWorld(container, name);
			world.timeKeeper = timeBuilder.build(world);
			WorldManager.get().addWorld(world);
			return world;
		}
	}
}
