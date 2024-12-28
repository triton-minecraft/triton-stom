package dev.kyriji.tritonstom.worlds.time;

public class TimeManager {
	private TimeManager() {}

	public TimeKeeper.Builder buildTimeKeeper() {
		return new TimeKeeper.Builder();
	}

	private static final class InstanceHolder {
		public static final TimeManager INSTANCE = new TimeManager();
	}

	public static TimeManager get() {
		return InstanceHolder.INSTANCE;
	}
}
