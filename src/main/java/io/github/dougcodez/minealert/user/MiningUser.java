package io.github.dougcodez.minealert.user;

import io.github.dougcodez.minealert.utils.FormatUtils;
import lombok.Getter;
import org.bukkit.entity.Player;

public class MiningUser {

    @Getter
    private final Player player;

    public MiningUser(Player player) {
        this.player = player;
    }


    public void sendMessage(String message) {
        player.sendMessage(FormatUtils.color(message));
    }

}
