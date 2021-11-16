package me.Mung.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleCloudDatabase {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(OracleCloudDatabase.class);

    public int ShowTable() throws SQLException {
        String sql = "select * from LA.char_check";

        try {
            Statement stmt  = DBConnection.getConnection().createStatement();
            ResultSet rs = (ResultSet) stmt.executeQuery(sql);
            while (rs.next()) {
                LOGGER.info("{}", rs.getString(1) );
            }
            DBConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
