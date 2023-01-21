package io.github.dougcodez.minealert.cache;

import io.github.dougcodez.minealert.MineAlert;
import io.github.dougcodez.minealert.minedata.properties.MiningDataProperties;
import io.github.dougcodez.minealert.user.MiningUser;
import io.github.dougcodez.minealert.user.MiningUserManager;
import io.github.dougcodez.minealert.user.data.MiningUserDataHandler;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class CacheSystem {

    private final MiningUserManager userManager = MineAlert.getInstance().getMiningUserManager();
    private final MiningUserDataHandler dataHandler = MineAlert.getInstance().getUserDataHandler();

    public static void saveOnDisable() {
        if (userManager.getUsers().isEmpty() || userManager.getUsers() == null) return;
        for (MiningUser users : userManager.getUsers()) {
            UUID uuid = users.getPlayer().getUniqueId();
            MineAlert.getInstance().getUserDataHandler()
                    .getMiningDataProperties()
                    .forEach(dataProperties -> dataProperties.getCacheMap().keySet().forEach(player -> dataProperties.sendCacheDataToDatabase(uuid)));
        }
    }

    public void loopCacheSystem() {
        if (userManager.getUsers().isEmpty() || userManager.getUsers() == null) return;
        for (MiningUser users : userManager.getUsers()) {
            UUID uuids = users.getPlayer().getUniqueId();
            dataHandler.savePlayerData(uuids);
            for (MiningDataProperties dataProperties : dataHandler.getMiningDataProperties()) {
                dataProperties.addToMineStatistic(uuids);
            }
        }
    }
}
