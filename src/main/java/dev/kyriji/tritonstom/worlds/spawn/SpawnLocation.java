package dev.kyriji.tritonstom.worlds.spawn;

import dev.kyriji.tritonstom.worlds.TritonWorld;
import net.minestom.server.coordinate.Pos;

public record SpawnLocation(TritonWorld world, Pos position) { }
