package me.Mung.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static me.Mung.util.DBConnection.ds;
import static me.Mung.util.DBConnection.rs;

public class OracleCloudDatabase {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(OracleCloudDatabase.class);

    public int ShowTable() throws SQLException {
        String sql = "select * from LA.char_check";

        try {
            Statement stmt  = ds.getConnection().createStatement();
            rs = (ResultSet) stmt.executeQuery(sql);
            while (rs.next()) {
                LOGGER.info("{}", rs.getString(1) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
