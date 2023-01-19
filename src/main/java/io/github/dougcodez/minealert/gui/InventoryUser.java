package io.github.dougcodez.minealert.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class InventoryUser {

    private String name;
    private UUID uuid;
    private AbstractMenu abstractMenu;

    public InventoryUser(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player toPlayer() {
        return Bukkit.getPlayer(name);
    }

    public AbstractMenu getAbstractMenu() {
        return abstractMenu;
    }

    public void setAbstractMenu(AbstractMenu abstractMenu) {
        this.abstractMenu = abstractMenu;
    }
}
