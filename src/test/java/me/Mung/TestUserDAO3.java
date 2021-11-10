package me.Mung;

import me.Mung.Model.UserDAO;
import me.Mung.Model.UserVO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class TestUserDAO3 {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestUserDAO3.class);

    @Test
    public void TestF() throws SQLException {
        UserVO userVO = new UserVO();
        userVO.setId_name("c");
        userVO.setChar_name("asd");
        userVO.setCur_level(1445.0);
        UserDAO.deleteUser(userVO);
    }
}