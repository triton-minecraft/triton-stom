package dev.kyriji.tritonstom.world.util;

import net.minestom.server.coordinate.BlockVec;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;

public class Location {
	private Instance instance;
	private double x;
	private double y;
	private double z;
	private float yaw;
	private float pitch;

	public Location(Instance instance, Pos pos) {
		this(instance, pos.x(), pos.y(), pos.z(), pos.yaw(), pos.pitch());
	}

	public Location(Instance instance, BlockVec blockVec) {
		this(instance, blockVec.x(), blockVec.y(), blockVec.z());
	}

	public Location(Instance instance, double x, double y, double z) {
		this(instance, x, y, z, 0, 0);
	}

	public Location(Instance instance, double x, double y, double z, float yaw, float pitch) {
		this.instance = instance;
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public Instance getInstance() {
		return instance;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public Pos toPos() {
		return new Pos(x, y, z);
	}

	public String toPrettyBlockPosString() {
		return String.format("%d, %d, %d", (int)x, (int)y, (int)z);
	}
}
