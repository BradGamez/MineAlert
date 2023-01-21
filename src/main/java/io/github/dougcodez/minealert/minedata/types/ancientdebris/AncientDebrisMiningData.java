package io.github.dougcodez.minealert.minedata.types.ancientdebris;

import com.google.common.collect.Maps;
import io.github.dougcodez.minealert.builder.ItemBuilder;
import io.github.dougcodez.minealert.minedata.properties.MiningDataProperties;
import io.github.dougcodez.minealert.utils.Version;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.Map;
import java.util.UUID;

public class AncientDebrisMiningData extends MiningDataProperties {

    private final Map<UUID, Integer> ancientdebrisCacheMap = Maps.newConcurrentMap();

    @Override
    public int getSlot() {
        return getInspectConfig().getInt("inspect.ancient_debris.item-slot");
    }

    @Override
    public ItemStack getMenuIcon() {
        if (!matchesPriorities()) {
            return getNonSupportedIcon();
        }
        return ItemBuilder.Builder.getInstance()
                .itemType(Material.valueOf(getInspectConfig().getString("inspect.ancient_debris.item-type")))
                .itemAmount(1)
                .itemName(getInspectConfig().getString("inspect.ancient_debris.item-name"))
                .itemLore(getInspectConfig().getStringList("inspect.ancient_debris.item-lore"))
                .build();
    }

    @Override
    public int getMinVL() {
        return getMineAlertSettingsConfig().getInt("alert.ancient_debris.vl");
    }

    @Override
    public boolean isEnabled() {
        return getMineAlertSettingsConfig().getBoolean("alert.ancient_debris.enable");
    }

    @Override
    public Version getSupportedVersion() {
        return Version.v1_16_R1;
    }

    @Override
    public String getMineStatisticName() {
        return "ANCIENTDEBRIS";
    }

    @Override
    public Map<UUID, Integer> getCacheMap() {
        return ancientdebrisCacheMap;
    }
}