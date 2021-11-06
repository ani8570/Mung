package me.Mung;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static me.Mung.util.DBConnection.ds;
import static me.Mung.util.DBConnection.rs;

public class TestDB1 {

    @Test
    public static void main(String[] args) throws SQLException {
        String sql = "select * from test_table";

        try {
            Statement stmt  = ds.getConnection().createStatement();
            rs = (ResultSet) stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
