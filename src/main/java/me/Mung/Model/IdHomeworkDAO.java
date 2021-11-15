package me.Mung.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static me.Mung.util.DBConnection.ds;
import static me.Mung.util.DBConnection.rs;

public class IdHomeworkDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdHomeworkDAO.class);

    public static List<IdHomeworkVO> getIdHomeworkList(String id) {
        String sql = "select * from LA.Id_homework where id_name = ?";

        List<IdHomeworkVO> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                IdHomeworkVO player = new IdHomeworkVO();
                player.setId_name(rs.getString(1));
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
