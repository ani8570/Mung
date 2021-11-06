package me.Mung;

import me.Mung.util.OracleDatabase;
import org.junit.Test;

import java.sql.SQLException;
import static org.junit.Assert.assertNotNull;
public class TestDB1 {

    @Test
    public void jdbcTest1() throws SQLException {
//        String sql = "select * from test_table";
//
//        try {
//            Statement stmt  = ds.getConnection().createStatement();
//            rs = (ResultSet) stmt.executeQuery(sql);
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        OracleDatabase oracleDatabase = new OracleDatabase();
        assertNotNull("fail", oracleDatabase.ShowTable());
    }
}
