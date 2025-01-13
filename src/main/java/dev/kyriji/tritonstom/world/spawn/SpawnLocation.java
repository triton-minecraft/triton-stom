package dev.kyriji.tritonstom.world.spawn;

import dev.kyriji.tritonstom.world.TritonWorld;
import net.minestom.server.coordinate.Pos;

public record SpawnLocation(TritonWorld world, Pos position) { }
