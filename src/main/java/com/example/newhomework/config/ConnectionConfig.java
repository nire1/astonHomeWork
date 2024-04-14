package com.example.newhomework.config;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionConfig {
    Connection getConnection() throws SQLException;
}
