package io.github.dougcodez.minealert.file.lang;

import io.github.dougcodez.minealert.MineAlert;
import lombok.Getter;

@Getter
public enum Lang {

    PREFIX("prefix", "&7[&a&lMA&7] "),
    NO_PERMISSION("no-permission", "&cYou don't have enough permission to do that!"),
    NO_PLAYER_EXIST("no-player-exist", "&cThat player does not exist!"),

    NOTIFICATION_DESC("notification-desc", "&7Allows you turn on and off alerts"),
    NOTIFICATION_SYN("notification-syn", "&7/minealert notify"),
    NOTIFICATION_ENABLED("notification-enabled", "&7You have successfully enabled alert notifications!"),
    NOTIFICATION_DISABLED("notification-disabled", "&7You have successfully disabled alert notifications!"),

    INTERVAL_DESC("interval-desc", "&7Gives you the interval of when the cache system will loop!"),
    INTERVAL_SYN("interval-syn", "&7/minealert interval"),
    INTERVAL_MESSAGE("interval-message", "&7Interval: &a%amount%"),

    RELOAD_DESC("reload-desc", "&7Allows you to reload all config files"),
    RELOAD_SYN("reload-syn", "&7/minealert reload"),
    RELOAD_MESSAGE("reload-message","&aYou have successfully reloaded all the files!"),

    INSPECT_DESC("inspect-desc", "&7Allows you to view player mining statistics!"),
    INSPECT_SYN("inspect-syn", "&7/minealert inspect <player>"),
    INSPECT_MESSAGE("inspect-message", "&7You have opened the inspect menu for %player%"),


    VL_INTRODUCTION_MESSAGE("violations-intro-message", "&7%player% Mining Information for %ore%:"),
    VL_COUNTED_MESSAGE("violations-counted-message", "&7Cached &a%ore%  &7Mined VL: &f%amount%"),
    VL_COUNTED_OVERALL_MESSAGE("violations-counted-overall-message", "&7Overall &a%ore% &7Mined VL: &f%amount%"),

    ALERT_MESSAGE("alert-message", "&7%player% has mined &a%ore% &7[&a%amount%x&7]!"),
    ;

    public static final Lang[] CACHE = values();
    private final String path;
    private final String value;

    Lang(String path, String value) {
        this.path = path;
        this.value = value;
    }

    public String toConfigString() {
        return MineAlert.getInstance().getLangFile().getFileConfiguration().getString(this.path, this.value);
    }
}
