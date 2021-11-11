package me.Mung;

import me.Mung.Model.UserDAO;
import me.Mung.Model.UserVO;
import me.Mung.util.LACrawling;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class TestUserDAO1 {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestUserDAO1.class);

    @Test
    public void TestF() throws SQLException {
        UserVO userVO = new UserVO();
        userVO.setId_name("530042930732335113");
        userVO.setChar_name("남궁다");
        userVO.setCur_level(LACrawling.FindLevel(userVO.getChar_name()));
        UserDAO.insertUser(userVO);
        LOGGER.info("{}", userVO);
    }
    @Test
    public void TestF1() throws SQLException {
        UserVO userVO = new UserVO();
        userVO.setId_name("530042930732335113");
        userVO.setChar_name("dsp최승호");
        userVO.setCur_level(LACrawling.FindLevel(userVO.getChar_name()));
        UserDAO.insertUser(userVO);
        LOGGER.info("{}", userVO);
    }
    @Test
    public void TestF2() throws SQLException {
        UserVO userVO = new UserVO();
        userVO.setId_name("530042930732335113");
        userVO.setChar_name("최재화");
        userVO.setCur_level(LACrawling.FindLevel(userVO.getChar_name()));
        UserDAO.insertUser(userVO);
        LOGGER.info("{}", userVO);
    }
}
