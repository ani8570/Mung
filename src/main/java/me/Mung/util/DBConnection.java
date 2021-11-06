package me.Mung.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.Mung.CommandManager;
import me.Mung.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(DBConnection.class);
    private static final HikariConfig CONFIG = new HikariConfig();
    public static final HikariDataSource ds;
    public static ResultSet rs;

    static {
        CONFIG.setJdbcUrl(Config.get("URL"));
        CONFIG.setUsername(Config.get("Id"));
        CONFIG.setPassword(Config.get("Password"));
        ds = new HikariDataSource(CONFIG);
    }

    public DBConnection() {}
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
