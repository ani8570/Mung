package me.Mung.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static me.Mung.util.DBConnection.ds;
import static me.Mung.util.DBConnection.rs;

public class CharHomeworkDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CharHomeworkDAO.class);

    public static List<CharHomeworkVO> getIdHomeworkList(String id) {
        String sql = "select * from LA.Char_homework where char_name in (select char_name from player where id_name = ?) order by cur_lv";
        List<CharHomeworkVO> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CharHomeworkVO player = new CharHomeworkVO();
                player.setChar_name(rs.getString(1));
                player.setDungeon_name(rs.getString(2));
                player.setCnt(rs.getInt(3));
                list.add(player);
            }
        } catch (SQLException e) {
            LOGGER.error("GetList : {}", e.getMessage());
        }
        return list;
    }

}
