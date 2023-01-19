package io.github.dougcodez.minealert.command.types;

import io.github.dougcodez.minealert.MineAlert;
import io.github.dougcodez.minealert.command.SubCommand;
import io.github.dougcodez.minealert.file.lang.Lang;
import io.github.dougcodez.minealert.user.data.MiningUserDataHandler;
import io.github.dougcodez.minealert.utils.FormatUtils;
import io.github.dougcodez.minealert.utils.LoggerUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class NotifyCommand extends SubCommand {

    private final MiningUserDataHandler dataHandler = MineAlert.getInstance().getUserDataHandler();
    private final Set<UUID> staffNotifications = dataHandler.getStaffNotifications();

    @Override
    public String getName() {
        return "notifications";
    }

    @Override
    public String getDescription() {
        return Lang.NOTIFICATION_DESC.toConfigString();
    }

    @Override
    public String getSyntax() {
        return Lang.NOTIFICATION_SYN.toConfigString();
    }

    @Override
    public String getPermission() {
        return "minealert.staff";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if ((!(sender instanceof Player player))) {
            LoggerUtil.log("Only players can use this command!");
            return;
        }

        if (!player.hasPermission(getPermission())) {
            player.sendMessage(FormatUtils.color(Lang.PREFIX.toConfigString()) + Lang.NO_PERMISSION.toConfigString());
            return;
        }

        UUID playerUUID = player.getUniqueId();
        if (!staffNotifications.contains(playerUUID)) {
            staffNotifications.add(playerUUID);
            player.sendMessage(FormatUtils.color(Lang.PREFIX.toConfigString() + Lang.NOTIFICATION_ENABLED.toConfigString()));
        } else {
            staffNotifications.remove(playerUUID);
            player.sendMessage(FormatUtils.color(Lang.PREFIX.toConfigString() + Lang.NOTIFICATION_DISABLED.toConfigString()));
        }
    }
}
