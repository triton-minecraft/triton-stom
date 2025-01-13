package dev.kyriji.tritonstom.world.schematic;

import net.hollowcube.schem.Schematic;
import net.hollowcube.schem.SchematicBuilder;
import net.hollowcube.schem.SchematicReader;
import net.hollowcube.schem.SchematicWriter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class SchematicManager {
	private static SchematicManager INSTANCE;

	private SchematicManager() {
		MinecraftServer.getCommandManager().register(new SchematicCommand());
	}

	public void saveSchematic(Instance instance, Pos first, Pos second, String name) {
		SchematicBuilder builder = new SchematicBuilder();
		builder.addBlock(0, 0, 0, Block.OBSIDIAN);
		builder.addBlock(1, 0, 0, Block.OBSIDIAN);
		builder.addBlock(2, 2, 2, Block.OBSIDIAN);
		builder.addBlock(2, 0, 0, Block.STONE);
		// for (int x = first.blockX(); x <= second.blockX(); x++) {
		// 	for (int y = first.blockY(); y <= second.blockY(); y++) {
		// 		for (int z = first.blockZ(); z <= second.blockZ(); z++) {
		// 			builder.addBlock(first.x() - x, first.y() - y, first.z() - z, instance.getBlock(x, y, z));
		// 		}
		// 	}
		// }

		try {
			Files.createDirectories(Path.of("schematics"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			new SchematicWriter().write(builder.build(), Path.of("schematics", name + ".schematic"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void loadSchematic(Instance instance, Pos position, String name) {
		Path path = Path.of("schematics", name + ".schematic");

		if (!Files.exists(path)) {
			System.out.println("Schematic file does not exist: " + name);
			return;
		}

		Schematic schematic = new SchematicReader().read(path);
		System.out.println(Arrays.toString(schematic.blocks()));
		System.out.println(Arrays.toString(schematic.palette()));
	}

	public static void init() {
		if (INSTANCE != null) throw new IllegalStateException();
		INSTANCE = new SchematicManager();
	}

	public static SchematicManager get() {
		return INSTANCE;
	}
}
