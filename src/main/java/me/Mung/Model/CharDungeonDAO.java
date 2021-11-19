package me.Mung.Model;

import me.Mung.util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CharDungeonDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CharDungeonDAO.class);

    public static List<CharDungeonVO> getCharDungeonList() {
        String sql = "select * from LA.char_DUNGEON order by cur_lv";
        List<CharDungeonVO> list = new LinkedList<>();

        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CharDungeonVO charDungeonVO = new CharDungeonVO();
                charDungeonVO.setCur_lv(rs.getDouble(1));
                charDungeonVO.setDungeon_name(rs.getString(2));
                charDungeonVO.setSort_num(rs.getInt(3));
                list.add(charDungeonVO);
            }
            DBConnection.close();
        } catch (SQLException e) {
            LOGGER.error("GetList : {}", e.getMessage());
        }
        return list;
    }

    public static Map<Integer, List<String>> getCharDungeonMapbySortNum() {
        Map<Integer, List<String>> map = new LinkedHashMap<>();
        String sql = "select * from LA.char_DUNGEON order by cur_lv";
        try {
            Statement stmt = DBConnection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (!map.containsKey(rs.getInt(3))) {
                    map.put(rs.getInt(3), new ArrayList<>());
                }
                map.get(rs.getInt(3)).add(rs.getString(2));
            }
            DBConnection.close();
        } catch (SQLException e) {
            LOGGER.error("GetMap : {}", e.getMessage());
        }
        return map;
    }
}
