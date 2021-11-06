package me.Mung.util;

import me.Mung.CommandManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static me.Mung.util.DBConnection.ds;
import static me.Mung.util.DBConnection.rs;

public class OracleDatabase {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(OracleDatabase.class);

    public int ShowTable() throws SQLException {
        String sql = "select * from test_table";

        try {
            Statement stmt  = ds.getConnection().createStatement();
            rs = (ResultSet) stmt.executeQuery(sql);
            while (rs.next()) {
                LOGGER.info("{}", rs.getInt(1) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
