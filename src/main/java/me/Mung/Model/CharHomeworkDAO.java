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

    public static List<CharHomeworkVO> getCharHomeworkList(String charName) {
        String sql = "select * from LA.Char_homework where char_name = ?"; //order by cur_lv";
        List<CharHomeworkVO> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setString(1, charName);
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

    public static void updateCharHomework(String char_name, int cnt, List<String> dungeon_name) {
        String sql = "update LA.char_homework set cnt = ? where char_name = ? and DUNGEON_NAME = ?";

        List<IdHomeworkVO> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setString(1, String.valueOf(cnt));
            pstmt.setString(2, char_name);
            for (String s : dungeon_name) {
                pstmt.setString(3, s);
                pstmt.executeUpdate();
            }
            DBConnection.close();
        } catch (SQLException e) {
            LOGGER.error("update : {}", e.getMessage());
        }
    }

    public static void resetDailyCharHomework() {
        String sql = "update LA.char_homework\n" +
                "set cnt = 0\n" +
                "where (char_name, dungeon_name) in\n" +
                "(select h.char_name, h.dungeon_name\n" +
                "from LA.char_dungeon d, LA.char_homework h\n" +
                "where d.sort_num = 1\n" +
                "and d.dungeon_name = h.dungeon_name)";

        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.executeUpdate();
            DBConnection.close();
        } catch (SQLException e) {
            LOGGER.error("update : {}", e.getMessage());
        }
    }

    public static void resetWeeklyCharHomework() {
        String sql = "update LA.char_homework set cnt = 0";

        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.executeUpdate();
            DBConnection.close();
        } catch (SQLException e) {
            LOGGER.error("update : {}", e.getMessage());
        }
    }
}
