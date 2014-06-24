package com.peoplewearus.web.spring.db;

import com.mysql.jdbc.NonRegisteringDriver;
import com.mysql.jdbc.Statement;

import java.sql.*;

public final class ConnectionManager {
    private volatile boolean isInitialized;
    private Driver driver;
    private final String url;
    private final String username;
    private final String password;

    public ConnectionManager(String host, String database, String username, String password) {
        this.url = String.format("jdbc:mysql://%s/%s", host, database);
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() {
        if (isInitialized) {
            try {
                return DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new RuntimeException("Could not get connection to: " + url, e);
            }
        } else {
            throw new IllegalStateException("Connection manager must be initialized before getConnection() is called.");
        }
    }

    public void closeResource(Object resource) {
        if (resource == null) return;
        try {
            if (resource instanceof ResultSet) ((ResultSet) resource).close();
            if (resource instanceof Statement) ((Statement) resource).close();
            if (resource instanceof Connection) ((Connection) resource).close();
        } catch (SQLException e) {
            throw new RuntimeException("Could not close resource", e);
        }
    }

    synchronized ConnectionManager initialize() {
        try {
            driver = new NonRegisteringDriver();
            DriverManager.registerDriver(driver);
            isInitialized = true;
            return this;
        } catch (Exception e) {
            throw new RuntimeException("Could not initialize ConnectionManager. Driver registration failed.", e);
        }
    }

    synchronized ConnectionManager destroy() {
        try {
            DriverManager.deregisterDriver(driver);
            return this;
        } catch (Exception e) {
            throw new RuntimeException("Could not destroy ConnectionManager. Driver deregistration failed.", e);
        }
    }

}
