package io.github.dougcodez.minealert.command.types;

import io.github.dougcodez.minealert.MineAlert;
import io.github.dougcodez.minealert.command.SubCommand;
import io.github.dougcodez.minealert.file.lang.Lang;
import io.github.dougcodez.minealert.utils.FormatUtils;
import org.bukkit.command.CommandSender;

public class IntervalCommand extends SubCommand {

    @Override
    public String getName() {
        return "interval";
    }

    @Override
    public String getDescription() {
        return Lang.INTERVAL_DESC.toConfigString();
    }

    @Override
    public String getSyntax() {
        return Lang.INTERVAL_SYN.toConfigString();
    }

    @Override
    public String getPermission() {
        return "minealert.staff";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage(FormatUtils.color(Lang.PREFIX.toConfigString()) + Lang.NO_PERMISSION.toConfigString());
            return;
        }
        sender.sendMessage(FormatUtils.color(Lang.PREFIX.toConfigString() + Lang.INTERVAL_MESSAGE.toConfigString().replace("%amount%", String.valueOf(MineAlert.getInterval()))));
    }
}
