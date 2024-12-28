package dev.kyriji.tritonstom.worlds.spawn;

import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;

public interface SpawnLocationProvider {

	SpawnLocation getSpawnLocation(AsyncPlayerConfigurationEvent event);
}
