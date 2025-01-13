package dev.kyriji.tritonstom.world.edit;

import dev.kyriji.tritonstom.world.util.Location;
import net.minestom.server.instance.Instance;

public class WorldEditSelection {
	private Instance instance;
	private Location first;
	private Location second;

	public WorldEditSelection() {
		this(null);
	}

	public WorldEditSelection(Location first) {
		this(first, null);
	}

	public WorldEditSelection(Location first, Location second) {
		this.first = first;
		this.second = second;
	}

	public Instance getInstance() {
		return instance;
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	public Location getFirst() {
		return first;
	}

	public void setFirst(Location first) {
		this.first = first;
	}

	public Location getSecond() {
		return second;
	}

	public void setSecond(Location second) {
		this.second = second;
	}

	public boolean isComplete() {
		return first != null && second != null;
	}

	public void clear() {
		instance = null;
		first = null;
		second = null;
	}
}
