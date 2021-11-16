package me.Mung;

import me.Mung.Model.CharDungeonDAO;
import me.Mung.Model.CharDungeonVO;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class SimpleTest {
    private static final org.slf4j.Logger LOGGER = (Logger) LoggerFactory.getLogger(SimpleTest.class);

    //    @RepeatedTest(20)
    @Test
    public void TestF() {
        String s = "남궁다";
        String s1 = "dsp최승호";
        String s2 = "1234";

        LOGGER.info("{} {} {}", s.getBytes().length, s1.getBytes().length, s2.getBytes().length);
    }
}
