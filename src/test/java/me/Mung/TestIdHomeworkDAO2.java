package me.Mung;

import me.Mung.Model.IdHomeworkDAO;
import me.Mung.Model.IdHomeworkVO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class TestIdHomeworkDAO2 {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestIdHomeworkDAO2.class);
    Map<String, Map<String, Integer>> map;

    //    @RepeatedTest(20)
    @Test
    public void TestF() {
        LOGGER.info("{}", IdHomeworkDAO.getIdHomeworkList("530042930732335113").toString());
        IdHomeworkDAO.updateIdHomework("530042930732335113",1, Arrays.asList("데자뷰", "도비스"));
        LOGGER.info("{}", IdHomeworkDAO.getIdHomeworkList("530042930732335113").toString());
    }
}
