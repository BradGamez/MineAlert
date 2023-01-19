package io.github.dougcodez.minealert.listener;

import io.github.dougcodez.minealert.MineAlert;
import io.github.dougcodez.minealert.file.lang.Lang;
import io.github.dougcodez.minealert.minedata.properties.MiningDataProperties;
import io.github.dougcodez.minealert.user.data.MiningUserDataHandler;
import io.github.dougcodez.minealert.utils.FormatUtils;
import io.github.dougcodez.minealert.utils.Version;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Set;
import java.util.UUID;

import static org.bukkit.Material.ANCIENT_DEBRIS;

public class BlockBreakListener implements Listener {

    private final MiningUserDataHandler dataHandler = MineAlert.getInstance().getUserDataHandler();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();
        Block block = e.getBlock();
        Material blockType = block.getType();
        if (player.isOp() || player.hasPermission("minealert.admin")) return;
        if (player.getGameMode() == GameMode.CREATIVE) return;
        switch (blockType) {
            case IRON_ORE -> {
                if (hasSilkTouch(e)) {
                    e.setCancelled(true);
                    return;
                }
                dataHandler.getIronMiningData().addToMineStatistic(playerUUID);
                alertStaff(player, dataHandler.getIronMiningData());
            }
            case GOLD_ORE -> {
                if (hasSilkTouch(e)) {
                    e.setCancelled(true);
                    return;
                }
                dataHandler.getGoldMiningData().addToMineStatistic(playerUUID);
                alertStaff(player, dataHandler.getGoldMiningData());
            }
            case LAPIS_ORE -> {
                if (hasSilkTouch(e)) {
                    e.setCancelled(true);
                    return;
                }
                dataHandler.getLapisMiningData().addToMineStatistic(playerUUID);
                alertStaff(player, dataHandler.getLapisMiningData());
            }
            case REDSTONE_ORE -> {
                if (hasSilkTouch(e)) {
                    e.setCancelled(true);
                    return;
                }
                dataHandler.getRedstoneMiningData().addToMineStatistic(playerUUID);
                alertStaff(player, dataHandler.getRedstoneMiningData());
            }
            case DIAMOND_ORE -> {
                if (hasSilkTouch(e)) {
                    e.setCancelled(true);
                    return;
                }
                dataHandler.getDiamondMiningData().addToMineStatistic(playerUUID);
                alertStaff(player, dataHandler.getDiamondMiningData());
            }
            case EMERALD_ORE -> {
                if (hasSilkTouch(e)) {
                    e.setCancelled(true);
                    return;
                }
                dataHandler.getEmeraldMiningData().addToMineStatistic(playerUUID);
                alertStaff(player, dataHandler.getEmeraldMiningData());
            }
        }


        if (Version.getServerVersion(Bukkit.getServer()).isNewerOrSameThan(Version.v1_16_R1)) {
            if (blockType == ANCIENT_DEBRIS) {
                if (hasSilkTouch(e)) {
                    e.setCancelled(true);
                    return;
                }
                dataHandler.getAncientDebrisMiningData().addToMineStatistic(playerUUID);
                alertStaff(player, dataHandler.getAncientDebrisMiningData());
            }
        }

        if (Version.getServerVersion(Bukkit.getServer()).isNewerOrSameThan(Version.v1_17_R1)) {
            switch (blockType) {
                case COPPER_ORE -> {
                    if (hasSilkTouch(e)) {
                        e.setCancelled(true);
                        return;
                    }
                    dataHandler.getCopperMiningData().addToMineStatistic(playerUUID);
                    alertStaff(player, dataHandler.getCopperMiningData());
                }

                case DEEPSLATE_COPPER_ORE -> {
                    if (hasSilkTouch(e)) {
                        e.setCancelled(true);
                        return;
                    }
                    dataHandler.getDsCopperMiningData().addToMineStatistic(playerUUID);
                    alertStaff(player, dataHandler.getDsCopperMiningData());
                }

                case DEEPSLATE_IRON_ORE -> {
                    if (hasSilkTouch(e)) {
                        e.setCancelled(true);
                        return;
                    }
                    dataHandler.getDsIronMiningData().addToMineStatistic(playerUUID);
                    alertStaff(player, dataHandler.getDsIronMiningData());
                }

                case DEEPSLATE_GOLD_ORE -> {
                    if (hasSilkTouch(e)) {
                        e.setCancelled(true);
                        return;
                    }
                    dataHandler.getDsGoldMiningData().addToMineStatistic(playerUUID);
                    alertStaff(player, dataHandler.getDsGoldMiningData());
                }

                case DEEPSLATE_LAPIS_ORE -> {
                    if (hasSilkTouch(e)) {
                        e.setCancelled(true);
                        return;
                    }
                    dataHandler.getDsLapisMiningData().addToMineStatistic(playerUUID);
                    alertStaff(player, dataHandler.getDsLapisMiningData());
                }

                case DEEPSLATE_REDSTONE_ORE -> {
                    if (hasSilkTouch(e)) {
                        e.setCancelled(true);
                        return;
                    }
                    dataHandler.getDsRedstoneMiningData().addToMineStatistic(playerUUID);
                    alertStaff(player, dataHandler.getDsRedstoneMiningData());
                }

                case DEEPSLATE_DIAMOND_ORE -> {
                    if (hasSilkTouch(e)) {
                        e.setCancelled(true);
                        return;
                    }
                    dataHandler.getDsDiamondMiningData().addToMineStatistic(playerUUID);
                    alertStaff(player, dataHandler.getDsDiamondMiningData());
                }

                case DEEPSLATE_EMERALD_ORE -> {
                    if (hasSilkTouch(e)) {
                        e.setCancelled(true);
                        return;
                    }
                    dataHandler.getDsEmeraldMiningData().addToMineStatistic(playerUUID);
                    alertStaff(player, dataHandler.getDsEmeraldMiningData());
                }
            }
        }
    }


    private void alertStaff(Player player, MiningDataProperties dataProperty) {
        if (!dataProperty.matchesPriorities()) return;
        UUID playerUUID = player.getUniqueId();
        if (dataProperty.getCacheValue(playerUUID) >= dataProperty.getMinVL()) {
            Set<UUID> staffNotifications = dataHandler.getStaffNotifications();
            for (UUID uuid : staffNotifications) {
                Player staff = Bukkit.getPlayer(uuid);
                if (staff != null) {
                    staff.sendMessage(FormatUtils.color(Lang.PREFIX.toConfigString() + Lang.ALERT_MESSAGE.toConfigString()
                            .replace("%player%", player.getName())
                            .replace("%ore%", dataProperty.getMineStatisticName())
                            .replace("%amount%", String.valueOf(dataProperty.getCacheValue(playerUUID)))));
                }
            }
        }
    }

    public boolean hasSilkTouch(BlockBreakEvent e) {
        for(MiningDataProperties properties : MineAlert.getInstance().getUserDataHandler().getMiningDataProperties()) {
            if (!properties.matchesPriorities()) return false;
        }
        Player player = e.getPlayer();
        return player.getInventory().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH);
    }
}
