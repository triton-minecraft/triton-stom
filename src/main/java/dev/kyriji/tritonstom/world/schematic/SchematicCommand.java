package dev.kyriji.tritonstom.world.schematic;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

public class SchematicCommand extends Command {
	public SchematicCommand() {
		super("schematic");

		setDefaultExecutor((sender, context) -> {
			if (!(sender instanceof Player player)) return;

			player.sendMessage("--------------------");
			player.sendMessage(" > /schematic load <schematic_name>");
		});

		ArgumentEnum<SubCommand> subCommandArgument = ArgumentType.Enum("subcommand", SubCommand.class)
				.setFormat(ArgumentEnum.Format.LOWER_CASED);
		ArgumentString schematicNameArgument = ArgumentType.String("schematic_name");

		addSyntax((sender, context) -> {
			if (!(sender instanceof Player player)) return;

			SubCommand subCommand = context.get(subCommandArgument);
			if (subCommand != SubCommand.LOAD) return;

			String schematicName = context.get(schematicNameArgument);
			SchematicManager.get().loadSchematic(player.getInstance(), player.getPosition(), schematicName);
		}, subCommandArgument, schematicNameArgument);
	}

	public enum SubCommand {
		LOAD
	}
}
