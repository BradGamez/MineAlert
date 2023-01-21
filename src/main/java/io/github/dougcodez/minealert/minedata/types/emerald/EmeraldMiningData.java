package io.github.dougcodez.minealert.minedata.types.emerald;

import com.google.common.collect.Maps;
import io.github.dougcodez.minealert.builder.ItemBuilder;
import io.github.dougcodez.minealert.minedata.properties.MiningDataProperties;
import io.github.dougcodez.minealert.utils.Version;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.Map;
import java.util.UUID;

public class EmeraldMiningData extends MiningDataProperties {

    private final Map<UUID, Integer> emeraldCacheMap = Maps.newConcurrentMap();

    @Override
    public int getSlot() {
        return getInspectConfig().getInt("inspect.emerald.item-slot");
    }

    @Override
    public ItemStack getMenuIcon() {
        if (!matchesPriorities()) {
            return getNonSupportedIcon();
        }

        return ItemBuilder.Builder.getInstance()
                .itemType(Material.valueOf(getInspectConfig().getString("inspect.emerald.item-type")))
                .itemAmount(1)
                .itemName(getInspectConfig().getString("inspect.emerald.item-name"))
                .itemLore(getInspectConfig().getStringList("inspect.emerald.item-lore"))
                .build();
    }

    @Override
    public int getMinVL() {
        return getMineAlertSettingsConfig().getInt("alert.emerald.vl");
    }

    @Override
    public boolean isEnabled() {
        return getMineAlertSettingsConfig().getBoolean("alert.emerald.enable");
    }

    @Override
    public Version getSupportedVersion() {
        return Version.v1_8_R1;
    }


    @Override
    public String getMineStatisticName() {
        return "EMERALD";
    }

    @Override
    public Map<UUID, Integer> getCacheMap() {
        return emeraldCacheMap;
    }
}