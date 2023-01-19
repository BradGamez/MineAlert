package io.github.dougcodez.minealert.command.types;

import io.github.dougcodez.minealert.MineAlert;
import io.github.dougcodez.minealert.command.SubCommand;
import io.github.dougcodez.minealert.file.lang.Lang;
import io.github.dougcodez.minealert.utils.FormatUtils;
import org.bukkit.command.CommandSender;

public class ReloadFilesCommand extends SubCommand {

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return Lang.RELOAD_DESC.toConfigString();
    }

    @Override
    public String getSyntax() {
        return Lang.RELOAD_SYN.toConfigString();
    }

    @Override
    public String getPermission() {
        return "minealert.admin";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage(FormatUtils.color(Lang.PREFIX.toConfigString()) + Lang.NO_PERMISSION.toConfigString());
            return;
        }

        MineAlert.getInstance().getMineAlertSettingsFile().reloadFile("mine-alert-settings.yml");
        MineAlert.getInstance().getLangFile().reloadFile("messages.yml");
        MineAlert.getInstance().getDatabaseFile().reloadFile("database-settings.yml");
        sender.sendMessage(FormatUtils.color(Lang.PREFIX.toConfigString() + Lang.RELOAD_MESSAGE));
    }
}
