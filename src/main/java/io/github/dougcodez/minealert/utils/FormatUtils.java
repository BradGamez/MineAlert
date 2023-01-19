package io.github.dougcodez.minealert.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class FormatUtils {

    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
