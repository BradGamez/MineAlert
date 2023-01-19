package io.github.dougcodez.minealert.listener;

import io.github.dougcodez.minealert.MineAlert;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Bukkit.getScheduler().runTaskAsynchronously(MineAlert.getInstance(), () -> MineAlert.getInstance().getUserDataHandler().loadUserData(player));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        MineAlert.getInstance().getUserDataHandler().saveUserData(player);
    }
}
