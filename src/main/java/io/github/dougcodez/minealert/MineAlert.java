package io.github.dougcodez.minealert;

import io.github.dougcodez.minealert.cache.CacheSystem;
import io.github.dougcodez.minealert.command.MineAlertCommand;
import io.github.dougcodez.minealert.command.SubCommandRegistry;
import io.github.dougcodez.minealert.file.DatabaseFile;
import io.github.dougcodez.minealert.file.MineAlertSettingsFile;
import io.github.dougcodez.minealert.file.inventory.InspectMenuSettingsFile;
import io.github.dougcodez.minealert.file.lang.LangFile;
import io.github.dougcodez.minealert.gui.listener.GUIListeners;
import io.github.dougcodez.minealert.listener.BlockBreakListener;
import io.github.dougcodez.minealert.listener.ConnectionListener;
import io.github.dougcodez.minealert.mysql.HikariClientInit;
import io.github.dougcodez.minealert.mysql.api.StatementAPI;
import io.github.dougcodez.minealert.user.MiningUserManager;
import io.github.dougcodez.minealert.user.data.MiningUserDataHandler;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;

@Getter
public class MineAlert extends JavaPlugin {

    private StatementAPI statementAPI;
    private LangFile langFile;
    private DatabaseFile databaseFile;
    private MineAlertSettingsFile mineAlertSettingsFile;
    private InspectMenuSettingsFile inspectMenuSettingsFile;
    private MiningUserManager miningUserManager;
    private MiningUserDataHandler userDataHandler;

    @Getter
    private static int interval;

    public void onEnable() {
        registerInstantiations();
        registerRegistries();

        interval = mineAlertSettingsFile.getFileConfiguration().getInt("interval");
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            interval--;
            if (interval <= 0) {
                CompletableFuture.runAsync(CacheSystem::loopCacheSystem).whenComplete((unused, throwable) -> interval = MineAlert.getInstance().getMineAlertSettingsFile().getFileConfiguration().getInt("interval"));
            }
        }, 20, 20);
    }

    public void onDisable() {
        CacheSystem.saveOnDisable();
    }

    private void registerInstantiations() {
        langFile = new LangFile();
        databaseFile = new DatabaseFile();
        mineAlertSettingsFile = new MineAlertSettingsFile();
        inspectMenuSettingsFile = new InspectMenuSettingsFile();
        statementAPI = new StatementAPI();
        miningUserManager = new MiningUserManager();
        userDataHandler = new MiningUserDataHandler();
    }

    private void registerRegistries() {
        registerFiles();
        registerDatabase();
        registerCommands();
        registerListeners();
    }

    private void registerFiles() {
        langFile.registerFile();
        databaseFile.registerFile();
        mineAlertSettingsFile.registerFile();
        inspectMenuSettingsFile.registerFile();

    }

    private void registerDatabase() {
        new HikariClientInit().initDatabase();
    }

    private void registerCommands() {
        SubCommandRegistry.registerCommands();
        getCommand("minealert").setExecutor(new MineAlertCommand());
    }

    private void registerListeners() {
        addListener(new GUIListeners(), new ConnectionListener(), new BlockBreakListener());
    }

    private void addListener(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }

    public static MineAlert getInstance() {
        return MineAlert.getPlugin(MineAlert.class);
    }
}
