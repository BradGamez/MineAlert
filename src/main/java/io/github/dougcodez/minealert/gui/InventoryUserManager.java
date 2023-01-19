package io.github.dougcodez.minealert.gui;

import lombok.experimental.UtilityClass;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class InventoryUserManager {

    private Map<UUID, InventoryUser> inventoryUserMap = new ConcurrentHashMap<>();


    public void addUser(String name, UUID uuid) {
        inventoryUserMap.putIfAbsent(uuid, new InventoryUser(name, uuid));
    }

    public void removeUser(UUID uuid) {
        inventoryUserMap.remove(uuid);
    }

    public Map<UUID, InventoryUser> getInventoryUserMap() {
        return inventoryUserMap;
    }
}
