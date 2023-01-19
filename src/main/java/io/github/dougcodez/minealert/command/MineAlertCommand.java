package io.github.dougcodez.minealert.command;

import io.github.dougcodez.minealert.file.lang.Lang;
import io.github.dougcodez.minealert.utils.FormatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MineAlertCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender s, @NotNull Command command, @NotNull String label, String[] args) {
        if (!s.hasPermission("minealert.help")) {
            s.sendMessage(FormatUtils.color(Lang.PREFIX.toConfigString() + Lang.NO_PERMISSION.toConfigString()));
            return true;
        }

        if (args.length > 0) {
            for (SubCommand subCommand : SubCommandRegistry.getSubCommandList()) {
                if (args[0].equalsIgnoreCase(subCommand.getName())) {
                    subCommand.perform(s, args);
                }
            }
        } else {
            for (SubCommand subCommand : SubCommandRegistry.getSubCommandList()) {
                s.sendMessage(FormatUtils.color(Lang.PREFIX.toConfigString() + subCommand.getSyntax() + " - " + subCommand.getDescription()));
            }
        }
        return true;
    }
}
