package me.Mung;

import me.Mung.Model.CharDungeonDAO;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


public class TestCharDungeonDAO2 {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestCharDungeonDAO2.class);

    @RepeatedTest(20)
    public void TestF() {
        Map<Integer, List<String>> map = CharDungeonDAO.getCharDungeonMapbySortNum();
        map.forEach((key, val) -> LOGGER.info("{} {}", key, val));
    }


}
