package me.Mung.Model;

import me.Mung.util.LACrawling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static me.Mung.util.DBConnection.ds;
import static me.Mung.util.DBConnection.rs;

public class PlayerDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerDAO.class);

    public static List<PlayerVO> getCharList(String id) {
        String sql = "select * from LA.PLAYER " +
                "where id_name = ?" +
                "order by cur_lv DESC";
        List<PlayerVO> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PlayerVO user = new PlayerVO();
                user.setId_name(rs.getString(1));
                user.setChar_name(rs.getString(2));
                user.setCur_level(LACrawling.FindLevel(user.getChar_name()));
//                updatePlayer(user);
                list.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("GetList : {}", e);
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param user
     * @return success : 1, fail : 0
     */
    public static int insertPlayer(PlayerVO user) {
        String sql = "{call insert_player(?,?,?)}";
        try {
            CallableStatement pstmt = ds.getConnection().prepareCall(sql);
            pstmt.setString(1, user.getId_name());
            pstmt.setString(2, user.getChar_name());
            pstmt.setDouble(3, user.getCur_level());
            pstmt.executeQuery();
        } catch (SQLException e) {
            LOGGER.error("Insert : {}", e);
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public static int deletePlayer(PlayerVO user) {
        String sql = "{call delete_player(?,?,?)}";
        try {
            CallableStatement pstmt = ds.getConnection().prepareCall(sql);
            pstmt.setString(1, user.getId_name());
            pstmt.setString(2, user.getChar_name());
            pstmt.setDouble(3, user.getCur_level());
            pstmt.executeQuery();
        } catch (SQLException e) {
            LOGGER.error("Delete : {}", e);
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public static void updatePlayer(PlayerVO user) {
        String sql = "update LA.player set cur_lv = ? where char_name = ?";
        try {
            PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
            pstmt.setDouble(1, user.getCur_level());
            pstmt.setString(2, user.getChar_name());
            pstmt.executeQuery();
        } catch (SQLException e) {
            LOGGER.error("Update : {}", e);
            e.printStackTrace();
        }
    }
}
