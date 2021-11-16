package me.Mung;

import me.Mung.Model.PlayerDAO;
import me.Mung.Model.PlayerVO;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class TestPlayerDAO {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestPlayerDAO.class);

    @RepeatedTest(15)
    public void TestF() throws SQLException {
        List<PlayerVO> list = PlayerDAO.getCharList("530042930732335113");
        list.forEach(user -> LOGGER.info("{}", user));
    }
}
