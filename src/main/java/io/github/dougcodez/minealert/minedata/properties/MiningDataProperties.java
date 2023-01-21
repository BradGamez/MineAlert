package io.github.dougcodez.minealert.minedata.properties;

import io.github.dougcodez.minealert.MineAlert;
import io.github.dougcodez.minealert.builder.ItemBuilder;
import io.github.dougcodez.minealert.mysql.utils.CommonQueryTool;
import io.github.dougcodez.minealert.utils.Version;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class MiningDataProperties {

    public abstract int getSlot();

    public abstract ItemStack getMenuIcon();

    public abstract int getMinVL();

    public abstract boolean isEnabled();

    public abstract Version getSupportedVersion();

    public void addToMineStatistic(UUID uuid) {
        Map<UUID, Integer> cacheMap = getCacheMap();
        if (!cacheMap.containsKey(uuid)) {
            cacheMap.put(uuid, 0);
        } else {
            cacheMap.put(uuid, cacheMap.get(uuid) + 1);
        }
    }

    public void sendCacheDataToDatabase(UUID uuid) {
        if (!matchesPriorities()) return;
        Map<UUID, Integer> cacheMap = getCacheMap();
        if (cacheMap.containsKey(uuid)) {
            CommonQueryTool.setMineStatisticAmount(uuid, getMineStatisticName(), CommonQueryTool.getMineStatistic(uuid, getMineStatisticName()) + cacheMap.get(uuid));
        }
    }

    public int getCacheValue(UUID uuid) {
        if (!matchesPriorities()) return -1;
        return getCacheMap().getOrDefault(uuid, 0);
    }

    public int getExactValue(UUID uuid) {
        if (!matchesPriorities()) return -1;
        return CommonQueryTool.getMineStatistic(uuid, getMineStatisticName()) + getCacheMap().get(uuid);
    }

    public void removeFromCacheMap(UUID uuid) {
        getCacheMap().remove(uuid);
    }

    public FileConfiguration getInspectConfig() {
        return MineAlert.getInstance().getInspectMenuSettingsFile().getFileConfiguration();
    }

    public FileConfiguration getMineAlertSettingsConfig() {
        return MineAlert.getInstance().getMineAlertSettingsFile().getFileConfiguration();
    }

    public abstract String getMineStatisticName();

    public abstract Map<UUID, Integer> getCacheMap();

    public boolean matchesPriorities() {
        return isEnabled() && Version.getServerVersion(Bukkit.getServer()).isNewerOrSameThan(getSupportedVersion());
    }

    public ItemStack getNonSupportedIcon() {
        List<String> defaultLore = Collections.singletonList("&cDisabled Ore or Unsupported Version!");
        return ItemBuilder.Builder.getInstance()
                .itemType(Material.BARRIER)
                .itemAmount(1)
                .itemName("&c&lX")
                .itemLore(defaultLore)
                .build();
    }
}
