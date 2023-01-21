package io.github.dougcodez.minealert.minedata.types.gold;

import com.google.common.collect.Maps;
import io.github.dougcodez.minealert.builder.ItemBuilder;
import io.github.dougcodez.minealert.minedata.properties.MiningDataProperties;
import io.github.dougcodez.minealert.utils.Version;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.Map;
import java.util.UUID;

public class GoldMiningData extends MiningDataProperties {

    private final Map<UUID, Integer> goldCacheMap = Maps.newConcurrentMap();

    @Override
    public int getSlot() {
        return getInspectConfig().getInt("inspect.gold.item-slot");
    }

    @Override
    public ItemStack getMenuIcon() {
        if (!matchesPriorities()) {
            return getNonSupportedIcon();
        }

        return ItemBuilder.Builder.getInstance()
                .itemType(Material.valueOf(getInspectConfig().getString("inspect.gold.item-type")))
                .itemAmount(1)
                .itemName(getInspectConfig().getString("inspect.gold.item-name"))
                .itemLore(getInspectConfig().getStringList("inspect.gold.item-lore"))
                .build();
    }

    @Override
    public int getMinVL() {
        return getMineAlertSettingsConfig().getInt("alert.gold.vl");
    }

    @Override
    public boolean isEnabled() {
        return getMineAlertSettingsConfig().getBoolean("alert.gold.enable");
    }

    @Override
    public Version getSupportedVersion() {
        return Version.v1_8_R1;
    }


    @Override
    public String getMineStatisticName() {
        return "GOLD";
    }

    @Override
    public Map<UUID, Integer> getCacheMap() {
        return goldCacheMap;
    }
}

