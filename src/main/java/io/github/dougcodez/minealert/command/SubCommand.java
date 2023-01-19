package io.github.dougcodez.minealert.command;

import org.bukkit.command.CommandSender;

public abstract class SubCommand {

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract String getPermission();

    public abstract void perform(CommandSender sender, String[] args);
}
