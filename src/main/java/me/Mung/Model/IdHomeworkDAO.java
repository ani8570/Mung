package me.Mung.Model;

import me.Mung.util.LACrawling;
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
        String sql = "select * from LA.Id_homework" +
                "where id_name = ?";
        List<IdHomeworkVO> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                IdHomeworkVO user = new IdHomeworkVO();
                user.setId_name(rs.getString(1));
                user.setDungeon_name(rs.getString(2));
                user.setCheck_num(rs.getInt(3));
                list.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("GetList : {}", e);
            e.printStackTrace();
        }
        return list;
    }
}
