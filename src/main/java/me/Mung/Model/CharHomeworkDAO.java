package me.Mung.Model;

import me.Mung.util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CharHomeworkDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CharHomeworkDAO.class);

    public static List<CharHomeworkVO> getCharHomeworkList(String id) {
        String sql = "select * from LA.Char_homework where char_name in (select char_name from player where id_name = ?)"; //order by cur_lv";
        List<CharHomeworkVO> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CharHomeworkVO player = new CharHomeworkVO();
                player.setChar_name(rs.getString(1));
                player.setDungeon_name(rs.getString(2));
                player.setCnt(rs.getInt(3));
                list.add(player);
            }
            DBConnection.close();
        } catch (SQLException e) {
            LOGGER.error("GetList : {}", e.getMessage());
        }
        return list;
    }

    public static Map<String, Map<String, Integer>> getCharHomeworkMap(String id) {
//        String sql = "select char_homework.char_name, char_homework.dungeon_name, char_homework.cnt from Char_homework, player\n" +
//                "where char_homework.char_name = player.char_name\n" +
//                "and Char_homework.char_name in (select player.char_name from player where player.id_name = ?)\n" +
//                "order by player.cur_lv desc"; //order by cur_lv";
        String sql = "select * from LA.Char_homework where char_name in (select char_name from player where id_name = ?)"; //order by cur_lv";
        Map<String, Map<String, Integer>> map = new LinkedHashMap<>();
        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (!map.containsKey(rs.getString(2))) {
                    map.put(rs.getString(2), new LinkedHashMap<>());
                }
                map.get(rs.getString(2))
                        .put(rs.getString(1), rs.getInt(3));
            }
            DBConnection.close();
        } catch (SQLException e) {
            LOGGER.error("GetMap : {}", e.getMessage());
            return null;
        }
        return map;
    }
}
