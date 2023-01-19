package io.github.dougcodez.minealert.user.data;

import io.github.dougcodez.minealert.MineAlert;
import io.github.dougcodez.minealert.minedata.properties.MiningDataProperties;
import io.github.dougcodez.minealert.minedata.types.ancientdebris.AncientDebrisMiningData;
import io.github.dougcodez.minealert.minedata.types.copper.CopperMiningData;
import io.github.dougcodez.minealert.minedata.types.copper.DSCopperMiningData;
import io.github.dougcodez.minealert.minedata.types.diamond.DSDiamondMiningData;
import io.github.dougcodez.minealert.minedata.types.diamond.DiamondMiningData;
import io.github.dougcodez.minealert.minedata.types.emerald.DSEmeraldMiningData;
import io.github.dougcodez.minealert.minedata.types.emerald.EmeraldMiningData;
import io.github.dougcodez.minealert.minedata.types.gold.DSGoldMiningData;
import io.github.dougcodez.minealert.minedata.types.gold.GoldMiningData;
import io.github.dougcodez.minealert.minedata.types.iron.DSIronMiningData;
import io.github.dougcodez.minealert.minedata.types.iron.IronMiningData;
import io.github.dougcodez.minealert.minedata.types.lapis.DSLapisMiningData;
import io.github.dougcodez.minealert.minedata.types.lapis.LapisMiningData;
import io.github.dougcodez.minealert.minedata.types.redstone.DSRedstoneMiningData;
import io.github.dougcodez.minealert.minedata.types.redstone.RedstoneMiningData;
import io.github.dougcodez.minealert.mysql.api.StatementAPI;
import io.github.dougcodez.minealert.mysql.utils.CommonQueryTool;
import io.github.dougcodez.minealert.mysql.utils.UUIDConvertTool;
import io.github.dougcodez.minealert.user.MiningUser;
import io.github.dougcodez.minealert.user.MiningUserManager;
import io.github.dougcodez.minealert.utils.LoggerUtil;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Getter
public class MiningUserDataHandler {

    private final MiningUserManager userManager = MineAlert.getInstance().getMiningUserManager();
    private final StatementAPI statementAPI = MineAlert.getInstance().getStatementAPI();
    private final CopperMiningData copperMiningData = new CopperMiningData();
    private final DSCopperMiningData dsCopperMiningData = new DSCopperMiningData();
    private final IronMiningData ironMiningData = new IronMiningData();
    private final DSIronMiningData dsIronMiningData = new DSIronMiningData();
    private final GoldMiningData goldMiningData = new GoldMiningData();
    private final DSGoldMiningData dsGoldMiningData = new DSGoldMiningData();
    private final LapisMiningData lapisMiningData = new LapisMiningData();
    private final DSLapisMiningData dsLapisMiningData = new DSLapisMiningData();
    private final RedstoneMiningData redstoneMiningData = new RedstoneMiningData();
    private final DSRedstoneMiningData dsRedstoneMiningData = new DSRedstoneMiningData();
    private final DiamondMiningData diamondMiningData = new DiamondMiningData();
    private final DSDiamondMiningData dsDiamondMiningData = new DSDiamondMiningData();
    private final EmeraldMiningData emeraldMiningData = new EmeraldMiningData();
    private final DSEmeraldMiningData dsEmeraldMiningData = new DSEmeraldMiningData();
    private final AncientDebrisMiningData ancientDebrisMiningData = new AncientDebrisMiningData();
    private final Set<UUID> staffNotifications = Collections.synchronizedSet(new LinkedHashSet<>());

    private final List<MiningDataProperties> miningDataProperties = Collections.synchronizedList(Arrays.asList(
            copperMiningData,
            dsCopperMiningData,
            ironMiningData,
            dsIronMiningData,
            goldMiningData,
            dsGoldMiningData,
            lapisMiningData,
            dsLapisMiningData,
            redstoneMiningData,
            dsRedstoneMiningData,
            diamondMiningData,
            dsDiamondMiningData,
            emeraldMiningData,
            dsEmeraldMiningData,
            ancientDebrisMiningData
    ));


    public void loadUserData(Player player) {
        UUID playerUUID = player.getUniqueId();
        InputStream binaryUUID = UUIDConvertTool.convertUniqueId(playerUUID);
        String name = player.getName();
        userManager.addPlayer(player);
        if (!CommonQueryTool.isUserInTheDatabase(playerUUID)) {
            String insertQuery = "INSERT INTO MINEDATA (UUID, NAME, COPPER, DSCOPPER, IRON, DSIRON, GOLD, DSGOLD, LAPIS, DSLAPIS, REDSTONE, DSREDSTONE, DIAMOND, DSDIAMOND, EMERALD, DSEMERALD, ANCIENTDEBRIS)" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            statementAPI.executeUpdate(insertQuery, ps -> {
                ps.setBinaryStream(1, binaryUUID);
                ps.setString(2, name);
                for (int i = 3; i <= 17; i++) {
                    ps.setInt(i, 0);
                }

                //LoggerUtil.log(name + " has been inserted to the database!");
            });
        }

        for (MiningDataProperties properties : miningDataProperties) {
            if(properties.matchesPriorities()) {
                properties.addToMineStatistic(playerUUID);
            }
        }

        if (player.hasPermission("minealert.staff")) {
            staffNotifications.add(playerUUID);
        }
    }

    public void saveUserData(Player player) {
        MiningUser miningUser = userManager.getUser(player);
        UUID playerUUID = miningUser.getPlayer().getUniqueId();
        CompletableFuture.runAsync(() -> {
            savePlayerData(playerUUID);
        }).whenComplete((aVoid, throwable) -> {
            userManager.removePlayer(player);
            staffNotifications.removeIf((t) -> staffNotifications.contains(playerUUID));
            //LoggerUtil.log("Successfully sent " + player.getName() + " data to the database!");
        });
    }

    public UUID savePlayerData(UUID playerUUID) {
        for (MiningDataProperties properties : getMiningDataProperties()) {
            properties.sendCacheDataToDatabase(playerUUID);
            properties.removeFromCacheMap(playerUUID);
        }
        return playerUUID;
    }
}
