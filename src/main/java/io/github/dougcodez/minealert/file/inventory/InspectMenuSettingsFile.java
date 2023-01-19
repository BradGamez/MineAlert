package io.github.dougcodez.minealert.file.inventory;

import io.github.dougcodez.minealert.file.AbstractFile;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.List;

public class InspectMenuSettingsFile extends AbstractFile {

    @Override
    public void registerFile() {
        createFile("inspect-inventory-settings.yml");
        setData();
        saveFile();
    }

    @Override
    public void setData() {
        if (isFileNotEmpty()) return;
        final FileConfiguration config = getFileConfiguration();
        final String prefix = "inspect.";
        final List<String> defaultLore = Arrays.asList("&fClick to see how much of the", "&fore the player mined!");
        config.set("menu-title", "&f%player% &7Mine Statistics");
        config.set("menu-slots", 54);

        config.set(prefix + "copper.item-slot", 1);
        config.set(prefix + "copper.item-type", "COPPER_ORE");
        config.set(prefix + "copper.item-name", "&7Copper Mined");
        config.set(prefix + "copper.item-lore", defaultLore);

        config.set(prefix + "ds_copper.item-slot", 2);
        config.set(prefix + "ds_copper.item-type", "DEEPSLATE_COPPER_ORE");
        config.set(prefix + "ds_copper.item-name", "&7Deep Slate Copper Mined");
        config.set(prefix + "ds_copper.item-lore", defaultLore);

        config.set(prefix + "iron.item-slot", 3);
        config.set(prefix + "iron.item-type", "IRON_ORE");
        config.set(prefix + "iron.item-name", "&7Iron Mined");
        config.set(prefix + "iron.item-lore", defaultLore);

        config.set(prefix + "ds_iron.item-slot", 4);
        config.set(prefix + "ds_iron.item-type", "DEEPSLATE_IRON_ORE");
        config.set(prefix + "ds_iron.item-name", "&7Deep Slate Iron Mined");
        config.set(prefix + "ds_iron.item-lore", defaultLore);

        config.set(prefix + "gold.item-slot", 5);
        config.set(prefix + "gold.item-type", "GOLD_ORE");
        config.set(prefix + "gold.item-name", "&7Gold Mined");
        config.set(prefix + "gold.item-lore", defaultLore);

        config.set(prefix + "ds_gold.item-slot", 6);
        config.set(prefix + "ds_gold.item-type", "DEEPSLATE_GOLD_ORE");
        config.set(prefix + "ds_gold.item-name", "&7Deep Slate Gold Mined");
        config.set(prefix + "ds_gold.item-lore", defaultLore);

        config.set(prefix + "lapis.item-slot", 7);
        config.set(prefix + "lapis.item-type", "LAPIS_ORE");
        config.set(prefix + "lapis.item-name", "&7Lapis Mined");
        config.set(prefix + "lapis.item-lore", defaultLore);

        config.set(prefix + "ds_lapis.item-slot", 10);
        config.set(prefix + "ds_lapis.item-type", "DEEPSLATE_LAPIS_ORE");
        config.set(prefix + "ds_lapis.item-name", "&7Deep Slate Lapis Mined");
        config.set(prefix + "ds_lapis.item-lore", defaultLore);

        config.set(prefix + "redstone.item-slot", 11);
        config.set(prefix + "redstone.item-type", "REDSTONE_ORE");
        config.set(prefix + "redstone.item-name", "&7Redstone Mined");
        config.set(prefix + "redstone.item-lore", defaultLore);

        config.set(prefix + "ds_redstone.item-slot", 12);
        config.set(prefix + "ds_redstone.item-type", "DEEPSLATE_REDSTONE_ORE");
        config.set(prefix + "ds_redstone.item-name", "&7Deep Slate Redstone Mined");
        config.set(prefix + "ds_redstone.item-lore", defaultLore);

        config.set(prefix + "diamond.item-slot", 13);
        config.set(prefix + "diamond.item-type", "DIAMOND_ORE");
        config.set(prefix + "diamond.item-name", "&7Diamonds Mined");
        config.set(prefix + "diamond.item-lore", defaultLore);

        config.set(prefix + "ds_diamond.item-slot", 14);
        config.set(prefix + "ds_diamond.item-type", "DEEPSLATE_DIAMOND_ORE");
        config.set(prefix + "ds_diamond.item-name", "&7Deep Slate Diamonds Mined");
        config.set(prefix + "ds_diamond.item-lore", defaultLore);

        config.set(prefix + "emerald.item-slot", 15);
        config.set(prefix + "emerald.item-type", "EMERALD_ORE");
        config.set(prefix + "emerald.item-name", "&7Emeralds Mined");
        config.set(prefix + "emerald.item-lore", defaultLore);

        config.set(prefix + "ds_emerald.item-slot", 16);
        config.set(prefix + "ds_emerald.item-type", "DEEPSLATE_EMERALD_ORE");
        config.set(prefix + "ds_emerald.item-name", "&7Deep Slate Emeralds Mined");
        config.set(prefix + "ds_emerald.item-lore", defaultLore);

        config.set(prefix + "ancient_debris.item-slot", 19);
        config.set(prefix + "ancient_debris.item-type", "ANCIENT_DEBRIS");
        config.set(prefix + "ancient_debris.item-name", "&7Ancient Debris Mined");
        config.set(prefix + "ancient_debris.item-lore", defaultLore);
    }
}
