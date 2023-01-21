package io.github.dougcodez.minealert.minedata.types.redstone;

import com.google.common.collect.Maps;
import io.github.dougcodez.minealert.builder.ItemBuilder;
import io.github.dougcodez.minealert.minedata.properties.MiningDataProperties;
import io.github.dougcodez.minealert.utils.Version;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.Map;
import java.util.UUID;

public class DSRedstoneMiningData extends MiningDataProperties {

    private final Map<UUID, Integer> dsRedstoneCacheMap = Maps.newConcurrentMap();

    @Override
    public int getSlot() {
        return getInspectConfig().getInt("inspect.ds_redstone.item-slot");
    }

    @Override
    public ItemStack getMenuIcon() {
        if (!matchesPriorities()) {
            return getNonSupportedIcon();
        }

        return ItemBuilder.Builder.getInstance()
                .itemType(Material.valueOf(getInspectConfig().getString("inspect.redstone.item-type")))
                .itemAmount(1)
                .itemName(getInspectConfig().getString("inspect.ds_redstone.item-name"))
                .itemLore(getInspectConfig().getStringList("inspect.ds_redstone.item-lore"))
                .build();
    }

    @Override
    public int getMinVL() {
        return getMineAlertSettingsConfig().getInt("alert.ds_redstone.vl");
    }

    @Override
    public boolean isEnabled() {
        return getMineAlertSettingsConfig().getBoolean("alert.ds_redstone.enable");
    }

    @Override
    public Version getSupportedVersion() {
        return Version.v1_17_R1;
    }

    @Override
    public String getMineStatisticName() {
        return "DSREDSTONE";
    }

    @Override
    public Map<UUID, Integer> getCacheMap() {
        return dsRedstoneCacheMap;
    }
}

