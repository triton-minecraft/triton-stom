package dev.kyriji.tritonstom.world.spawn;

import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;

public interface SpawnLocationProvider {

	SpawnLocation getSpawnLocation(AsyncPlayerConfigurationEvent event);
}
