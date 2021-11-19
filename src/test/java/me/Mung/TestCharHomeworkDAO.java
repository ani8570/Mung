package me.Mung;

import me.Mung.Model.CharHomeworkDAO;
import me.Mung.Model.CharHomeworkVO;
import org.junit.jupiter.api.RepeatedTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class TestCharHomeworkDAO {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestCharHomeworkDAO.class);

    @RepeatedTest(20)
    public void TestF() {
        List<CharHomeworkVO> list = CharHomeworkDAO.getAllCharHomeworkList("530042930732335113");
        list.forEach(hwk -> {
            LOGGER.info("{}", hwk);
        });
    }


}
