package dev.kyriji.tritonstom.worlds.time;

import dev.kyriji.tritonstom.worlds.TritonWorld;
import net.minestom.server.instance.InstanceContainer;

public class TimeKeeper {
	private final TritonWorld world;

	private TimeStrategy strategy;

	public TimeKeeper(TritonWorld world) {
		this.world = world;
	}

	public static class Builder {
		private TimeStrategy strategy = TimeStrategy.NORMAL;

		Builder() {}

		public Builder strategy(TimeStrategy strategy) {
			this.strategy = strategy;
			return this;
		}

		public TimeKeeper build(TritonWorld world) {
			InstanceContainer container = world.getInstance();

			switch (strategy) {
				case ALWAYS_NOON:
					container.setTime(6000);
					container.setTimeRate(0);
					break;
				case ALWAYS_MIDNIGHT:
					container.setTime(18000);
					container.setTimeRate(0);
					break;
			}

			return new TimeKeeper(world);
		}
	}
}
