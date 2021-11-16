package me.Mung.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.Mung.Config;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    private static final HikariConfig CONFIG = new HikariConfig();
    private static final HikariDataSource ds;
    private static Connection conn;

    static {
        CONFIG.setJdbcUrl(Config.get("Cloud_URL"));
        CONFIG.setUsername(Config.get("Cloud_Id"));
        CONFIG.setPassword(Config.get("Cloud_Password"));
        CONFIG.setConnectionTimeout(3000);

        ds = new HikariDataSource(CONFIG);

    }

    public DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        conn = ds.getConnection();
        return conn;
    }

    public static void close() throws SQLException {
        if (conn != null)
            conn.close();
    }
}
