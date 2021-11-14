package me.Mung.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.Mung.Config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    private static final HikariConfig CONFIG = new HikariConfig();
    public static final HikariDataSource ds;
    public static ResultSet rs;

    static {
//        CONFIG.setJdbcUrl("jdbc:oracle:thin:@edudb_high?TNS_ADMIN=C:\\\\github.com\\\\ani8570\\\\Mung\\\\Wallet_edudb");
//        CONFIG.setJdbcUrl("jdbc:oracle:thin:@edudb_high?TNS_ADMIN=Wallet_edudb");
        CONFIG.setJdbcUrl(Config.get("Cloud_URL"));
        CONFIG.setUsername(Config.get("Cloud_Id"));
        CONFIG.setPassword(Config.get("Cloud_Password"));
//        CONFIG.setMaxLifetime(30L);
//        CONFIG.time
//        CONFIG.setConnectionTimeout(40000L);
        ds = new HikariDataSource(CONFIG);
    }

    public DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
