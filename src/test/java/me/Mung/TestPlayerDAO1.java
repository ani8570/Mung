package me.Mung;

import me.Mung.Model.PlayerDAO;
import me.Mung.Model.PlayerVO;
import me.Mung.util.LACrawling;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class TestPlayerDAO1 {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestPlayerDAO1.class);

    @Test
    public void TestF() throws SQLException {
        PlayerVO userVO = new PlayerVO();
        userVO.setId_name("530042930732335113");
        userVO.setChar_name("남궁다");
        userVO.setCur_level(LACrawling.FindLevel(userVO.getChar_name()));
        PlayerDAO.insertPlayer(userVO);
        LOGGER.info("{}", userVO);
    }
    @Test
    public void TestF1() throws SQLException {
        PlayerVO userVO = new PlayerVO();
        userVO.setId_name("530042930732335113");
        userVO.setChar_name("dsp최승호");
        userVO.setCur_level(LACrawling.FindLevel(userVO.getChar_name()));
        PlayerDAO.insertPlayer(userVO);
        LOGGER.info("{}", userVO);
    }
    @Test
    public void TestF2() throws SQLException {
        PlayerVO userVO = new PlayerVO();
        userVO.setId_name("530042930732335113");
        userVO.setChar_name("최재화");
        userVO.setCur_level(LACrawling.FindLevel(userVO.getChar_name()));
        PlayerDAO.insertPlayer(userVO);
        LOGGER.info("{}", userVO);
    }
}
