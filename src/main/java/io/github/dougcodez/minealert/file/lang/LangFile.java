package io.github.dougcodez.minealert.file.lang;

import io.github.dougcodez.minealert.file.AbstractFile;
import org.bukkit.configuration.Configuration;

public class LangFile extends AbstractFile {

    @Override
    public void registerFile() {
        createFile("messages.yml");
        setData();
        saveFile();
    }

    @Override
    public void setData() {
        if (isFileNotEmpty()) return;
        final Configuration configuration = getFileConfiguration();
        for (Lang item : Lang.CACHE) {
            configuration.set(item.getPath(), item.getValue());
        }
    }
}
