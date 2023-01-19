package io.github.dougcodez.minealert.gui;

import io.github.dougcodez.minealert.utils.FormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AbstractMenu {


    private final Inventory abstractMenu;
    private final Map<Integer, AbstractAction> clickMap = new ConcurrentHashMap<>();

    /**
     * Column IDs:
     * inventories-by-UUID
     * opened-inventories
     */

    public AbstractMenu(int slots, String name, Player player) {
        this.abstractMenu = Bukkit.createInventory(null, slots, FormatUtils.color(name));
        player.openInventory(getAbstractMenu());
        InventoryUserManager.getInventoryUserMap().get(player.getUniqueId()).setAbstractMenu(this);
    }


    public void setItem(int slot, ItemStack item, AbstractAction menuAction) {
        abstractMenu.setItem(slot, item);
        clickMap.put(slot, menuAction);
    }


    public AbstractAction getAction(int slot) {
        return clickMap.get(slot);
    }

    public Inventory getAbstractMenu() {
        return abstractMenu;
    }


    public Map<Integer, AbstractAction> getActions() {
        return clickMap;
    }
}
