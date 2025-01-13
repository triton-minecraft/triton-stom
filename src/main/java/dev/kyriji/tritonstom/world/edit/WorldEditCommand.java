package dev.kyriji.tritonstom.world.edit;

import dev.kyriji.tritonstom.world.schematic.SchematicManager;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class WorldEditCommand extends Command {
	public WorldEditCommand() {
		super("we");

		setDefaultExecutor((sender, context) -> {
			sender.sendMessage("--------------------");
			sender.sendMessage(" > /we wand");
			sender.sendMessage(" > /we create_schematic");
		});

		ArgumentEnum<SubCommand> subCommandArgument = ArgumentType.Enum("subcommand", SubCommand.class)
				.setFormat(ArgumentEnum.Format.LOWER_CASED);
		ArgumentString schematicNameArgument = ArgumentType.String("schematic_name");

		addSyntax((sender, context) -> {
			if (!(sender instanceof Player player)) return;

			SubCommand subCommand = context.get(subCommandArgument);
			switch (subCommand) {
				case WAND:
					player.getInventory().addItemStack(ItemStack.of(Material.STONE_AXE));
					break;
				case CREATE_SCHEMATIC:
					player.sendMessage("usage: /we create_schematic <schematic_name>");
					break;
			}
		}, subCommandArgument);

		addSyntax((sender, context) -> {
			if (!(sender instanceof Player player)) return;

			SubCommand subCommand = context.get(subCommandArgument);
			if (subCommand != SubCommand.CREATE_SCHEMATIC) return;

			String schematicName = context.get(schematicNameArgument);
			WorldEditSelection selection = WorldEditManager.get().getSelectionMap().get(player);
			if (selection == null) {
				player.sendMessage("You must select a region first");
				return;
			} else if (!selection.isComplete()) {
				player.sendMessage("You must select both positions first");
				return;
			}

			SchematicManager.get().saveSchematic(selection.getInstance(), selection.getFirst().toPos(),
					selection.getSecond().toPos(), schematicName);
			player.sendMessage("Schematic created: " + schematicName);
		}, subCommandArgument, schematicNameArgument);
	}

	public enum SubCommand {
		WAND,
		CREATE_SCHEMATIC
	}
}
