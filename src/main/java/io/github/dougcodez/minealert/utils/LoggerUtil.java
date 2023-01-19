package io.github.dougcodez.minealert.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

import java.util.logging.Level;

@UtilityClass
public class LoggerUtil {

    public void log(String text){
        Bukkit.getLogger().log(Level.INFO, text);
    }
}
