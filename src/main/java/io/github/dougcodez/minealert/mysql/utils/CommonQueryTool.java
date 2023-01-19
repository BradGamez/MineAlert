package io.github.dougcodez.minealert.mysql.utils;

import io.github.dougcodez.minealert.MineAlert;
import io.github.dougcodez.minealert.mysql.api.StatementAPI;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@UtilityClass
public class CommonQueryTool {

    private final StatementAPI statementAPI = MineAlert.getInstance().getStatementAPI();

    public boolean isUserInTheDatabase(UUID binaryUUID) {
        AtomicBoolean isUserReal = new AtomicBoolean();
        statementAPI.executeQuery("SELECT * FROM MINEDATA WHERE UUID =?", ps ->
                ps.setBinaryStream(1, UUIDConvertTool.convertUniqueId(binaryUUID)), rs -> {
            if (rs.next()) {
                isUserReal.set(true);
            }
            return rs;
        });

        return isUserReal.get();
    }

    public boolean isUserInTheDatabase(String name) {
        AtomicBoolean isUserReal = new AtomicBoolean();
        statementAPI.executeQuery("SELECT * FROM MINEDATA WHERE NAME =?", ps ->
                ps.setString(1, name), rs -> {
            if (rs.next()) {
                isUserReal.set(true);
            }
            return rs;
        });

        return isUserReal.get();
    }

    public InputStream getUUIDByName(String name) {
        AtomicReference<InputStream> binaryUUID = new AtomicReference<>();
        statementAPI.executeQuery("SELECT * FROM MINEDATA WHERE NAME=?", ps -> {
            ps.setString(1, name);
        }, rs -> {
            if (rs.next()) {
                binaryUUID.set(rs.getBinaryStream("UUID"));
            }

            return rs;
        });

        return binaryUUID.get();
    }

    public int getMineStatistic(UUID binaryUUID, String mineStatistic) {
        AtomicInteger statisticAmount = new AtomicInteger();
        statementAPI.executeQuery("SELECT * FROM MINEDATA WHERE UUID =?", ps -> {
            ps.setBinaryStream(1, UUIDConvertTool.convertUniqueId(binaryUUID));
        }, rs -> {
            if (rs.next()) {
                statisticAmount.set(rs.getInt(mineStatistic));
            }

            return rs;
        });

        return statisticAmount.get();
    }

    public void setMineStatisticAmount(UUID binaryUUID, String mineStatistic, int amount) {
        statementAPI.executeUpdate("UPDATE MINEDATA SET " + mineStatistic + "=? WHERE UUID=?", ps -> {
            ps.setInt(1, amount);
            ps.setBinaryStream(2, UUIDConvertTool.convertUniqueId(binaryUUID));
        });
    }
}
