package io.github.dougcodez.minealert.minedata.types.iron;

import com.google.common.collect.Maps;
import io.github.dougcodez.minealert.builder.ItemBuilder;
import io.github.dougcodez.minealert.minedata.properties.MiningDataProperties;
import io.github.dougcodez.minealert.utils.Version;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.Map;
import java.util.UUID;

public class IronMiningData extends MiningDataProperties {

    private final Map<UUID, Integer> ironCacheMap = Maps.newConcurrentMap();

    @Override
    public int getSlot() {
        return getInspectConfig().getInt("inspect.iron.item-slot");
    }

    @Override
    public ItemStack getMenuIcon() {
        if (!matchesPriorities()) {
            return getNonSupportedIcon();
        }

        return ItemBuilder.Builder.getInstance()
                .itemType(Material.valueOf(getInspectConfig().getString("inspect.iron.item-type")))
                .itemAmount(1)
                .itemName(getInspectConfig().getString("inspect.iron.item-name"))
                .itemLore(getInspectConfig().getStringList("inspect.iron.item-lore"))
                .build();
    }

    @Override
    public int getMinVL() {
        return getMineAlertSettingsConfig().getInt("alert.iron.vl");
    }

    @Override
    public boolean isEnabled() {
        return getMineAlertSettingsConfig().getBoolean("alert.iron.enable");
    }

    @Override
    public Version getSupportedVersion() {
        return Version.v1_8_R1;
    }


    @Override
    public String getMineStatisticName() {
        return "IRON";
    }

    @Override
    public Map<UUID, Integer> getCacheMap() {
        return ironCacheMap;
    }
}
