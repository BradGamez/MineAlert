package io.github.dougcodez.minealert.mysql.api;

import java.sql.Connection;

public interface ConnectionType {

    Connection getConnection();
}
