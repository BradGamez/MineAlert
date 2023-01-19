package io.github.dougcodez.minealert.command;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class SubCommandRegistry {

    private final List<SubCommand> subCommandList = Lists.newArrayList();

    public void registerCommands() {
        for (SubCommandTypes all : SubCommandTypes.CACHE) {
            subCommandList.add(all.getSubCommand());
        }
    }

    public List<SubCommand> getSubCommandList() {
        return subCommandList;
    }
}
