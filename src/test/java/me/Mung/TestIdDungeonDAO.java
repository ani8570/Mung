package me.Mung;

import me.Mung.Model.IdDungeonDAO;
import me.Mung.Model.IdDungeonVO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class TestIdDungeonDAO {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestIdDungeonDAO.class);

    @Test
    public void TestF() {
        List<IdDungeonVO> list = IdDungeonDAO.getIdDungeonList();
        list.forEach(idDungeonVO -> LOGGER.info("{}", idDungeonVO));
    }
}
