package me.Mung;

import me.Mung.Model.CharHomeworkDAO;
import me.Mung.Model.IdHomeworkDAO;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;


public class TestCharHomeworkDAO3 {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestCharHomeworkDAO3.class);

    @Test
//    @RepeatedTest(20)
    public void TestF() {
//        String id = "788767702088810517";
        String id = "dsp최승호";
        LOGGER.info("{}", CharHomeworkDAO.getCharHomeworkList(id).toString());
        CharHomeworkDAO.updateCharHomework(id,1, Arrays.asList("가디언", "카던", "에포나"));
        LOGGER.info("{}", CharHomeworkDAO.getCharHomeworkList(id).toString());
        CharHomeworkDAO.updateCharHomework(id,0, Arrays.asList("가디언", "카던", "에포나"));
        LOGGER.info("{}", CharHomeworkDAO.getCharHomeworkList(id).toString());
    }


}
