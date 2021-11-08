package me.Mung;

import me.Mung.util.OracleCloudDatabase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static me.Mung.util.DBConnection.ds;
import static me.Mung.util.DBConnection.rs;
import static org.junit.Assert.assertNotNull;

public class TestDB2 {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestDB2.class);

    @Test
    public void jdbcTest2() throws SQLException {
        String sql = "select * from LA.char_check";

        try {
            Statement stmt  = ds.getConnection().createStatement();
            rs = (ResultSet) stmt.executeQuery(sql);
            while (rs.next()) {
                LOGGER.info("{}", rs.getString(1) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
