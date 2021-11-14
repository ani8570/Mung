package me.Mung;

import me.Mung.Model.PlayerDAO;
import me.Mung.Model.PlayerVO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class TestPlayerDAO4 {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestPlayerDAO4.class);

    @Test
    public void TestF() throws SQLException {
        PlayerVO userVO = new PlayerVO();
        userVO.setId_name("530042930732335113");
        userVO.setChar_name("남궁다");
        userVO.setCur_level(1445.0);
        PlayerDAO.deletePlayer(userVO);
    }
}
