package me.Mung;

import me.Mung.Model.CharHomeworkDAO;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TestCharHomeworkDAO2 {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestCharHomeworkDAO2.class);

//    @Test
    @RepeatedTest(20)
    public void TestF() {
//        String id = "788767702088810517";
        String id = "530042930732335113";
        Map<String, Map<String, Integer>> map = CharHomeworkDAO.getCharHomeworkMap(id);
        assert map != null;
        assert map.values() != null;
        map.forEach((key, val) -> {
            LOGGER.info("{} {}", key, val);
        });
        LOGGER.info("{}", map.isEmpty());
        LOGGER.info("{}", map.values());
        map.values().iterator().next().forEach((asd, n) -> {
            LOGGER.info("{}", asd);
        });

    }


}
