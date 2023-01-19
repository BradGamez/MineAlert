package io.github.dougcodez.minealert.file;

import org.bukkit.configuration.file.FileConfiguration;

public class MineAlertSettingsFile extends AbstractFile{

    @Override
    public void registerFile() {
        createFile("mine-alert-settings.yml");
        setData();
        saveFile();
    }

    @Override
    public void setData() {
        if (isFileNotEmpty()) return;
        final FileConfiguration config = getFileConfiguration();
        final String alertPrefix = "alert.";
        config.set("interval", 300);
        //Copper Category
        config.set(alertPrefix + "copper.vl", 30);
        config.set(alertPrefix + "copper.enable", true);
        config.set(alertPrefix + "ds_copper.vl", 30);
        config.set(alertPrefix + "ds_copper.enable", true);

        //Iron Category
        config.set(alertPrefix + "iron.vl", 25);
        config.set(alertPrefix + "iron.enable", true);
        config.set(alertPrefix + "ds_iron.vl", 25);
        config.set(alertPrefix + "ds_iron.enable", true);

        //Gold Category
        config.set(alertPrefix + "gold.vl", 20);
        config.set(alertPrefix + "gold.enable", true);
        config.set(alertPrefix + "ds_gold.vl", 15);
        config.set(alertPrefix + "ds_gold.enable", true);

        //Lapis
        config.set(alertPrefix + "lapis.vl", 20);
        config.set(alertPrefix + "lapis.enable", true);
        config.set(alertPrefix + "ds_lapis.vl", 15);
        config.set(alertPrefix + "ds_lapis.enable", true);

        //Redstone
        config.set(alertPrefix + "redstone.vl", 20);
        config.set(alertPrefix + "redstone.enable", true);
        config.set(alertPrefix + "ds_redstone.vl", 15);
        config.set(alertPrefix + "ds_redstone.enable", true);

        //Diamond
        config.set(alertPrefix + "diamond.vl", 5);
        config.set(alertPrefix + "diamond.enable", true);
        config.set(alertPrefix + "ds_diamond.vl", 3);
        config.set(alertPrefix + "ds_diamond.enable", true);

        //Emerald
        config.set(alertPrefix + "emerald.vl", 5);
        config.set(alertPrefix + "emerald.enable", true);
        config.set(alertPrefix + "ds_emerald.vl", 3);
        config.set(alertPrefix + "ds_emerald.enable", true);

        //Ancient Debris
        config.set(alertPrefix + "ancient_debris.vl", 2);
        config.set(alertPrefix + "ancient_debris.enable", true);
    }
}
