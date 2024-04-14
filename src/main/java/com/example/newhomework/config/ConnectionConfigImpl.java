package com.example.newhomework.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionConfigImpl implements ConnectionConfig {

    @Override
    public Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("datasource.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String url = properties.getProperty("url");
        String username = properties.getProperty("user");
        String password = properties.getProperty("password");


        return DriverManager.getConnection(url, username, password);
    }

    public static ConnectionConfigImpl create() {
        return new ConnectionConfigImpl();
    }

}
