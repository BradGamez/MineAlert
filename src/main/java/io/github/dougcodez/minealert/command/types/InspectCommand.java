package io.github.dougcodez.minealert.command.types;
import io.github.dougcodez.minealert.command.SubCommand;
import io.github.dougcodez.minealert.file.lang.Lang;
import io.github.dougcodez.minealert.gui.types.InspectMenu;
import io.github.dougcodez.minealert.utils.FormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InspectCommand extends SubCommand {

    @Override
    public String getName() {
        return "inspect";
    }

    @Override
    public String getDescription() {
        return Lang.INSPECT_DESC.toConfigString();
    }

    @Override
    public String getSyntax() {
        return Lang.INSPECT_SYN.toConfigString();
    }

    @Override
    public String getPermission() {
        return "minealert.staff";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length <= 1) return;
        if (!(sender instanceof final Player player)) {
            System.out.println("Only players can use this command!");
            return;
        }
        if (!player.hasPermission(getPermission())) {
            player.sendMessage(FormatUtils.color(Lang.PREFIX.toConfigString()) + Lang.NO_PERMISSION.toConfigString());
            return;
        }
        Player toView = Bukkit.getPlayer(args[1]);
        if (toView == null) {
            player.sendMessage(FormatUtils.color(Lang.PREFIX.toConfigString() + Lang.NO_PLAYER_EXIST.toConfigString()));
            return;
        }

        new InspectMenu(player, toView);
    }
}
