package io.github.dougcodez.minealert.gui.listener;

import io.github.dougcodez.minealert.gui.AbstractAction;
import io.github.dougcodez.minealert.gui.AbstractMenu;
import io.github.dougcodez.minealert.gui.InventoryUser;
import io.github.dougcodez.minealert.gui.InventoryUserManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.UUID;

public class GUIListeners implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String name = player.getName();
        UUID uuid = player.getUniqueId();
        InventoryUserManager.addUser(name, uuid);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        InventoryUserManager.removeUser(uuid);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player player)) {
            return;
        }
        UUID playerUUID = player.getUniqueId();
        if (InventoryUserManager.getInventoryUserMap().containsKey(playerUUID)) {
            AbstractMenu currentMenu = InventoryUserManager.getInventoryUserMap().get(playerUUID).getAbstractMenu();
            if (currentMenu != null) {
                e.setCancelled(true);
                AbstractAction action = currentMenu.getAction(e.getSlot());
                if (action != null) {
                    action.onClick(player);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClose(InventoryCloseEvent e) {
        UUID playerUUID = e.getPlayer().getUniqueId();
        Map<UUID, InventoryUser> inventoryUserMap = InventoryUserManager.getInventoryUserMap();

        if (!inventoryUserMap.containsKey(playerUUID)) {
            return;
        }
        InventoryUser user = inventoryUserMap.get(e.getPlayer().getUniqueId());
        if (user.getAbstractMenu() != null) {
            user.setAbstractMenu(null);
        }
    }
}
