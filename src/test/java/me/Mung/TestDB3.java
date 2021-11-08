package me.Mung;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static me.Mung.util.DBConnection.ds;
import static me.Mung.util.DBConnection.rs;

public class TestDB3 {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestDB3.class);

    @Test
    public void jdbcTest3() throws SQLException {
        String sql = "select * from LA.user_id where id_name = ?";

        try {
            PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
            pstmt.setString(1, "a");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                LOGGER.info("{} {} {}", rs.getString(1), rs.getString(2), rs.getInt(3) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
