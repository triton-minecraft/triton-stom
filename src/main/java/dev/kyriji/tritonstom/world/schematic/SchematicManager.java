package dev.kyriji.tritonstom.world.schematic;

import net.hollowcube.schem.SchematicBuilder;
import net.hollowcube.schem.SchematicWriter;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SchematicManager {
	private SchematicManager() {}

	public void createSchematic(Instance instance, Pos first, Pos second, String name) {
		SchematicBuilder builder = new SchematicBuilder();
		builder.addBlock(0, 0, 0, Block.OBSIDIAN);
		// for (int x = first.blockX(); x <= second.blockX(); x++) {
		// 	for (int y = first.blockY(); y <= second.blockY(); y++) {
		// 		for (int z = first.blockZ(); z <= second.blockZ(); z++) {
		// 			builder.addBlock(x, y, z, instance.getBlock(x, y, z));
		// 		}
		// 	}
		// }

		Path schematicsDir = Path.of("schematics");
		try {
			Files.createDirectories(schematicsDir);
		} catch (IOException e) {
			System.out.println("failed to create schematics directory: " + e.getMessage());
			throw new RuntimeException(e);
		}
		try {
			new SchematicWriter().write(builder.build(), Path.of("schematics", name + ".schematic"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static final class InstanceHolder {
		public static final SchematicManager INSTANCE = new SchematicManager();
	}

	public static SchematicManager get() {
		return InstanceHolder.INSTANCE;
	}
}
