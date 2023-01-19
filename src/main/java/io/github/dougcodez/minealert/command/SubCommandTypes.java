package io.github.dougcodez.minealert.command;

import io.github.dougcodez.minealert.command.types.InspectCommand;
import io.github.dougcodez.minealert.command.types.IntervalCommand;
import io.github.dougcodez.minealert.command.types.NotifyCommand;
import io.github.dougcodez.minealert.command.types.ReloadFilesCommand;

public enum SubCommandTypes {

    NOTIFY("notify", new NotifyCommand()),
    INTERVAL("interval", new IntervalCommand()),
    RELOAD_FILES("reload", new ReloadFilesCommand()),
    INSPECT("inspect", new InspectCommand());

    private final String name;
    private final SubCommand subCommand;
    public static final SubCommandTypes[] CACHE = values();

    SubCommandTypes(String name, SubCommand subCommand) {
        this.name = name;
        this.subCommand = subCommand;
    }

    public String getName() {
        return name;
    }

    public SubCommand getSubCommand() {
        return subCommand;
    }
}
