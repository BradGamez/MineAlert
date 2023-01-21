package io.github.dougcodez.minealert.minedata.types.diamond;

import com.google.common.collect.Maps;
import io.github.dougcodez.minealert.builder.ItemBuilder;
import io.github.dougcodez.minealert.minedata.properties.MiningDataProperties;
import io.github.dougcodez.minealert.utils.Version;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.Map;
import java.util.UUID;

public class DiamondMiningData extends MiningDataProperties {

    private final Map<UUID, Integer> diamondCacheMap = Maps.newConcurrentMap();

    @Override
    public int getSlot() {
        return getInspectConfig().getInt("inspect.diamond.item-slot");
    }

    @Override
    public ItemStack getMenuIcon() {
        if (!matchesPriorities()) {
            return getNonSupportedIcon();
        }
        return ItemBuilder.Builder.getInstance()
                .itemType(Material.valueOf(getInspectConfig().getString("inspect.diamond.item-type")))
                .itemAmount(1)
                .itemName(getInspectConfig().getString("inspect.diamond.item-name"))
                .itemLore(getInspectConfig().getStringList("inspect.diamond.item-lore"))
                .build();
    }

    @Override
    public int getMinVL() {
        return getMineAlertSettingsConfig().getInt("alert.diamond.vl");
    }

    @Override
    public boolean isEnabled() {
        return getMineAlertSettingsConfig().getBoolean("alert.diamond.enable");
    }

    @Override
    public String getMineStatisticName() {
        return "DIAMOND";
    }

    @Override
    public Version getSupportedVersion() {
        return Version.v1_8_R1;
    }

    @Override
    public Map<UUID, Integer> getCacheMap() {
        return diamondCacheMap;
    }
}
