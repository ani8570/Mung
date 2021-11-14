package me.Mung;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static me.Mung.util.DBConnection.ds;

public class TestDB {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestDB.class);

    @Test
    public void testDB(){
        try {
            Connection conn = ds.getConnection();
            LOGGER.info("{}", conn);
        } catch (SQLException e) {
            LOGGER.error("Fail");
        }
    }
}
