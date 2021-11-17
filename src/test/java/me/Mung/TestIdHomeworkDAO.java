package me.Mung;

import me.Mung.Model.IdHomeworkDAO;
import me.Mung.Model.IdHomeworkVO;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestIdHomeworkDAO {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TestIdHomeworkDAO.class);
    Map<String, Map<String, Integer>> map;

    //    @RepeatedTest(20)
    @Test
    public void TestF() {
        List<IdHomeworkVO> list = IdHomeworkDAO.getIdHomeworkList("788767702088810517");
        LOGGER.info("{}", list);
        list.forEach(IdHomeworkVO -> {
            LOGGER.info("{}", IdHomeworkVO);
//            LOGGER.info("{}", IdHomeworkVO.getM().get("530042930732335113").get("도비스"));
        });
        LOGGER.info("________________________");
        map = new HashMap<>();
        //아이디에 해당된 전체 리스트를 StringBuffer로 모아 reply로 출력
        map.put("530042930732335113", new HashMap<>());
        list.forEach(hwk -> {
            map.get(hwk.getId_name()).put(hwk.getDungeon_name(), hwk.getCnt());
        });
        LOGGER.info("{}", map);
    }
}
