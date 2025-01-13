package dev.kyriji.tritonstom.world.edit;

import dev.kyriji.tritonstom.world.util.Location;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class WorldEditManager {
	private static WorldEditManager INSTANCE;

	private static final Map<Player, WorldEditSelection> selectionMap = new HashMap<>();

	private WorldEditManager() {
		WorldEditListener.init();
		MinecraftServer.getCommandManager().register(new WorldEditCommand());
	}

	public void selectPosition(Player player, Location location, SelectionPosition position) {
		WorldEditSelection selection = selectionMap.computeIfAbsent(player, k -> new WorldEditSelection());
		if (selection.getInstance() == location.getInstance()) selection.clear();

		if (position == SelectionPosition.FIRST) {
			selection.setFirst(location);
			player.sendMessage("First position set to " + location.toPrettyBlockPosString());
		} else {
			selection.setSecond(location);
			player.sendMessage("Second position set to " + location.toPrettyBlockPosString());
		}
	}

	public Map<Player, WorldEditSelection> getSelectionMap() {
		return selectionMap;
	}

	public static void init() {
		if (INSTANCE != null) throw new IllegalStateException();
		INSTANCE = new WorldEditManager();
	}

	public static WorldEditManager get() {
		return INSTANCE;
	}
}
