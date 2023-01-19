package io.github.dougcodez.minealert.gui.types;

import io.github.dougcodez.minealert.MineAlert;
import io.github.dougcodez.minealert.file.lang.Lang;
import io.github.dougcodez.minealert.gui.AbstractMenu;
import io.github.dougcodez.minealert.minedata.properties.MiningDataProperties;
import io.github.dougcodez.minealert.user.data.MiningUserDataHandler;
import io.github.dougcodez.minealert.utils.FormatUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class InspectMenu extends AbstractMenu {


    @Getter
    private final Player target;

    public InspectMenu(Player player, Player target) {
        super(
                MineAlert.getInstance().getInspectMenuSettingsFile().getFileConfiguration().getInt("menu-slots"),
                MineAlert.getInstance().getInspectMenuSettingsFile().getFileConfiguration().getString("menu-title").replace("%player%", target.getName()),
                player
        );

        this.target = target;
        final MiningUserDataHandler userDataHandler = MineAlert.getInstance().getUserDataHandler();

        for (MiningDataProperties properties : userDataHandler.getMiningDataProperties()) {

            setItem(properties.getSlot(),
                    properties.getMenuIcon(),
                    (menuPlayer) -> Bukkit.getScheduler().runTaskAsynchronously(MineAlert.getInstance(), () -> {
                        String ore = properties.getMineStatisticName();
                        UUID targetUUID = target.getUniqueId();
                        String introMessage = FormatUtils.color(Lang.PREFIX.toConfigString() + Lang.VL_INTRODUCTION_MESSAGE.toConfigString().replace("%player%", target.getName()).replace("%ore%", ore));
                        String cachedVLMessage = FormatUtils.color(Lang.VL_COUNTED_MESSAGE.toConfigString().replace("%ore%", ore).replace("%amount%", String.valueOf(properties.getCacheValue(targetUUID))));
                        String overallVLMessage = FormatUtils.color(Lang.VL_COUNTED_OVERALL_MESSAGE.toConfigString().replace("%ore%", ore).replace("%amount%", String.valueOf(properties.getExactValue(targetUUID))));
                        menuPlayer.sendMessage(introMessage);
                        menuPlayer.sendMessage(cachedVLMessage);
                        menuPlayer.sendMessage(overallVLMessage);
                        Bukkit.getScheduler().runTask(MineAlert.getInstance(), menuPlayer::closeInventory);
                    }));

        }
    }
}
