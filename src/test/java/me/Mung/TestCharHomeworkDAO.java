package me.Mung;

import me.Mung.Model.CharHomeworkDAO;
import me.Mung.Model.CharHomeworkVO;
import me.Mung.Model.IdHomeworkDAO;
import me.Mung.Model.IdHomeworkVO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class TestCharHomeworkDAO {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestCharHomeworkDAO.class);
    Map<String, Map<String, Integer>> map;

    @Test
    public void TestF() {
        List<CharHomeworkVO> list = CharHomeworkDAO.getIdHomeworkList("530042930732335113");
        map = new LinkedHashMap<>();
        //아이디에 해당된 전체 리스트를 StringBuffer로 모아 reply로 출력
        list.forEach(hwk -> {
            LOGGER.info("{}", hwk);
            if (!map.containsKey(hwk.getChar_name())) {
                map.put(hwk.getChar_name(), new LinkedHashMap<>());
            }
            map.get(hwk.getChar_name()).put(hwk.getDungeon_name(), hwk.getCnt());
        });
        map.forEach((key, val) -> {
            LOGGER.info("{}", key);
            LOGGER.info("{}", val);
        });
    }
}
