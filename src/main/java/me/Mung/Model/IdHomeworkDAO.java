package me.Mung.Model;

import me.Mung.util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IdHomeworkDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdHomeworkDAO.class);

    public static List<IdHomeworkVO> getIdHomeworkList(String id) {
        String sql = "select * from LA.Id_homework where id_name = ?";

        List<IdHomeworkVO> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                IdHomeworkVO player = new IdHomeworkVO();
                player.setId_name(rs.getString(1));
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

    public static void updateIdHomework(String id, int cnt,List<String> dungeon_name) {
        String sql = "update LA.Id_homework set cnt = ? where id_name = ? and DUNGEON_NAME = ?";

        List<IdHomeworkVO> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setString(1, String.valueOf(cnt));
            pstmt.setString(2, id);
            for (String s : dungeon_name) {
                pstmt.setString(3, s);
                pstmt.executeUpdate();
            }
            DBConnection.close();
        } catch (SQLException e) {
            LOGGER.error("update : {}", e.getMessage());
        }
    }
}
