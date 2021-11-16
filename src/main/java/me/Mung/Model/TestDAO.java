package me.Mung.Model;

import me.Mung.util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDAO.class);
    private static Connection conn = null;

    public static int TestDAOF() {
        String sql = "select 1 from dual";
        try {
            conn = DBConnection.getConnection();
            conn.isValid(3000);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeQuery();
            DBConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
