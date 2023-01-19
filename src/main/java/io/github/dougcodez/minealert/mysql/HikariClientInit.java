package io.github.dougcodez.minealert.mysql;
import io.github.dougcodez.minealert.MineAlert;
import io.github.dougcodez.minealert.mysql.api.DatabaseInfo;
import io.github.dougcodez.minealert.mysql.api.HikariSetup;
import io.github.dougcodez.minealert.mysql.api.SQLTypes;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariClientInit extends HikariSetup {

    public HikariClientInit initDatabase() {
        FileConfiguration configuration = MineAlert.getInstance().getDatabaseFile().getFileConfiguration();
        final String host = configuration.getString("host");
        final int port = configuration.getInt("port");
        final String database = configuration.getString("database");
        final String user = configuration.getString("user");
        final String password = configuration.getString("password");
        DatabaseInfo infoRecord = new DatabaseInfo(host, port, database, user, password);
        init(SQLTypes.fromName(configuration.getString("driver")),
                infoRecord, configuration.getInt("timeout"), configuration.getInt("maxpool"));
        initTables();
        MineAlert.getInstance().getStatementAPI().setConnectionType(this);
        return this;
    }

    public void initTables() {
        String tableQuery = "CREATE TABLE IF NOT EXISTS MINEDATA(UUID BINARY(16) NOT NULL, NAME VARCHAR(16) NOT NULL, COPPER INT (4) NOT NULL, DSCOPPER INT (4) NOT NULL, IRON INT(4) NOT NULL, DSIRON INT(4) NOT NULL, " +
                "GOLD INT(4) NOT NULL, DSGOLD INT(4) NOT NULL, LAPIS INT(4) NOT NULL, DSLAPIS INT(4) NOT NULL, REDSTONE INT(4) NOT NULL, DSREDSTONE INT(4) NOT NULL, DIAMOND INT(4) NOT NULL, DSDIAMOND INT(4) NOT NULL, " +
                "EMERALD INT(4) NOT NULL, DSEMERALD INT(4) NOT NULL, ANCIENTDEBRIS INT(4) NOT NULL, PRIMARY KEY(UUID))";
        createTable(tableQuery);
    }

    @Override
    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
