package me.Mung;

import me.Mung.Model.PlayerDAO;
import me.Mung.Model.PlayerVO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class TestPlayerDAO2 {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestPlayerDAO2.class);

    @Test
    public void TestF() throws SQLException {
        PlayerVO userVO = new PlayerVO();
        userVO.setId_name("c");
        userVO.setChar_name("asd");
        userVO.setCur_level(1445.0);
        PlayerDAO.updatePlayer(userVO);
    }
}
