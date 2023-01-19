package io.github.dougcodez.minealert.file;

import org.bukkit.configuration.file.FileConfiguration;

public class DatabaseFile extends AbstractFile {

    @Override
    public void registerFile() {
        createFile("database-settings.yml");
        setData();
        saveFile();
    }

    @Override
    public void setData() {
        if (isFileNotEmpty()) return;
        final FileConfiguration config = getFileConfiguration();
        config.set("host", "localhost");
        config.set("port", 3306);
        config.set("database", "minealertdb");
        config.set("user", "username");
        config.set("password", "admin");
        config.set("driver", "MYSQL");
        config.set("timeout", 5000);
        config.set("maxpool", 10);
    }
}
