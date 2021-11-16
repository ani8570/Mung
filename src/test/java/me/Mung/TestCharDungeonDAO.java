package me.Mung;

import me.Mung.Model.CharDungeonDAO;
import me.Mung.Model.CharDungeonVO;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class TestCharDungeonDAO {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestCharDungeonDAO.class);

    @RepeatedTest(20)
    public void TestF() {
        List<CharDungeonVO> list = CharDungeonDAO.getCharDungeonList();
        list.forEach(CharDungeonVO -> LOGGER.info("{}", CharDungeonVO));
    }



}
