package me.Mung;

import me.Mung.Model.UserDAO;
import me.Mung.Model.UserVO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class TestUserDAO {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestUserDAO.class);

    @Test
    public void TestF() throws SQLException {
        List<UserVO> list = UserDAO.getCharList("530042930732335113");
        list.forEach(user -> LOGGER.info("{}" ,user));

    }
}
