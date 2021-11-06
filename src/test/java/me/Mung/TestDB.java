package me.Mung;

import oracle.jdbc.OracleTypes;
import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static me.Mung.util.DBConnection.ds;
import static me.Mung.util.DBConnection.rs;

public class TestDB {

    @Test
    public static void main(String[] args) throws SQLException {
        String sql = "{call CALLUSER(?)}";

        try {
            CallableStatement callableStatement = ds.getConnection().prepareCall(sql);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            rs = (ResultSet) callableStatement.getObject(1);
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
