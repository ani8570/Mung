package me.Mung;

import me.Mung.util.DBConnection;
import org.junit.jupiter.api.RepeatedTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDB {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestDB.class);

//    @Test
    @RepeatedTest(15)
    public void testDB(){
        try {
            Connection conn = DBConnection.getConnection();
            LOGGER.info("{}", conn);
            DBConnection.close();
        } catch (SQLException e) {
            LOGGER.error("Fail");
        }
    }
}
