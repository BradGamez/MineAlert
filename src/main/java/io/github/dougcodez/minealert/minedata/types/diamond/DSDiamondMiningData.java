package io.github.dougcodez.minealert.minedata.types.diamond;

import com.google.common.collect.Maps;
import io.github.dougcodez.minealert.builder.ItemBuilder;
import io.github.dougcodez.minealert.minedata.properties.MiningDataProperties;
import io.github.dougcodez.minealert.utils.Version;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public class DSDiamondMiningData extends MiningDataProperties {

    private final Map<UUID, Integer> dsDiamondCacheMap = Maps.newConcurrentMap();

    @Override
    public int getSlot() {
        return getInspectConfig().getInt("inspect.ds_diamond.item-slot");
    }

    @Override
    public ItemStack getMenuIcon() {
        if (!matchesPriorities()) {
            return getNonSupportedIcon();
        }

        return ItemBuilder.Builder.getInstance()
                .itemType(Material.valueOf(getInspectConfig().getString("inspect.ds_diamond.item-type")))
                .itemAmount(1)
                .itemName(getInspectConfig().getString("inspect.ds_diamond.item-name"))
                .itemLore(getInspectConfig().getStringList("inspect.ds_diamond.item-lore"))
                .build();
    }

    @Override
    public int getMinVL() {
        return getMineAlertSettingsConfig().getInt("alert.ds_diamond.vl");
    }

    @Override
    public boolean isEnabled() {
        return getMineAlertSettingsConfig().getBoolean("alert.ds_diamond.enable");
    }

    @Override
    public String getMineStatisticName() {
        return "DSDIAMOND";
    }

    @Override
    public Version getSupportedVersion() {
        return Version.v1_17_R1;
    }

    @Override
    public Map<UUID, Integer> getCacheMap() {
        return dsDiamondCacheMap;
    }
}


