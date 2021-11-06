package me.Mung;

import me.Mung.util.OracleCloudDatabase;
import me.Mung.util.OracleDatabase;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static me.Mung.util.DBConnection1.ds;
import static me.Mung.util.DBConnection1.rs;
import static org.junit.Assert.assertNotNull;

public class TestDB2 {

    @Test
    public void jdbcTest2() throws SQLException {
        OracleCloudDatabase oracleCloudDatabase = new OracleCloudDatabase();
        assertNotNull("fail", oracleCloudDatabase.ShowTable());
    }
}
