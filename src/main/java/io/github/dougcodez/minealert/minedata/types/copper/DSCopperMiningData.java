package io.github.dougcodez.minealert.minedata.types.copper;

import com.google.common.collect.Maps;
import io.github.dougcodez.minealert.builder.ItemBuilder;
import io.github.dougcodez.minealert.minedata.properties.MiningDataProperties;
import io.github.dougcodez.minealert.utils.Version;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.Map;
import java.util.UUID;

public class DSCopperMiningData extends MiningDataProperties {

    private final Map<UUID, Integer> dsCopperCacheMap = Maps.newConcurrentMap();

    @Override
    public int getSlot() {
        return getInspectConfig().getInt("inspect.ds_copper.item-slot");
    }

    @Override
    public ItemStack getMenuIcon() {
        if (!matchesPriorities()) {
            return getNonSupportedIcon();
        }

        return ItemBuilder.Builder.getInstance()
                .itemType(Material.valueOf(getInspectConfig().getString("inspect.ds_copper.item-type")))
                .itemAmount(1)
                .itemName(getInspectConfig().getString("inspect.ds_copper.item-name"))
                .itemLore(getInspectConfig().getStringList("inspect.ds_copper.item-lore"))
                .build();
    }

    @Override
    public int getMinVL() {
        return getMineAlertSettingsConfig().getInt("alert.ds_copper.vl");
    }

    @Override
    public boolean isEnabled() {
        return getMineAlertSettingsConfig().getBoolean("alert.ds_copper.enable");
    }

    @Override
    public String getMineStatisticName() {
        return "DSCOPPER";
    }

    @Override
    public Version getSupportedVersion() {
        return Version.v1_17_R1;
    }

    @Override
    public Map<UUID, Integer> getCacheMap() {
        return dsCopperCacheMap;
    }
}
