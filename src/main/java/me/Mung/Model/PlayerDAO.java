package me.Mung.Model;

import me.Mung.util.DBConnection;
import me.Mung.util.LACrawling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static me.Mung.util.DBConnection.*;

public class PlayerDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerDAO.class);

    public static List<PlayerVO> getCharList(String id) {
        String sql = "select * from LA.PLAYER where id_name = ? order by cur_lv DESC";
        List<PlayerVO> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PlayerVO player = new PlayerVO();
                player.setId_name(rs.getString(1));
                player.setChar_name(rs.getString(2));
                player.setCur_level(LACrawling.FindLevel(player.getChar_name()));
//                updatePlayer(player);
                list.add(player);
            }
            DBConnection.close();
        } catch (SQLException e) {
            LOGGER.error("GetList : {}", e.getMessage());
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
            CallableStatement pstmt = DBConnection.getConnection().prepareCall(sql);
            pstmt.setString(1, user.getId_name());
            pstmt.setString(2, user.getChar_name());
            pstmt.setDouble(3, user.getCur_level());
            pstmt.executeUpdate();
            DBConnection.close();
        } catch (SQLException e) {
            LOGGER.error("Insert : {}", e.getMessage());
            return 0;
        }
        return 1;
    }

    public static int deletePlayer(PlayerVO user) {
        String sql = "{call delete_player(?,?,?)}";
        try {
            CallableStatement pstmt = DBConnection.getConnection().prepareCall(sql);
            pstmt.setString(1, user.getId_name());
            pstmt.setString(2, user.getChar_name());
            pstmt.setDouble(3, user.getCur_level());
            pstmt.executeUpdate();
            DBConnection.close();
        } catch (SQLException e) {
            LOGGER.error("Delete : {}", e.getMessage());
            return 0;
        }
        return 1;
    }

    public static void updatePlayer(PlayerVO user) {
        String sql = "update LA.player set cur_lv = ? where char_name = ?";
        try {
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setDouble(1, user.getCur_level());
            pstmt.setString(2, user.getChar_name());
            pstmt.executeUpdate();
            DBConnection.close();
        } catch (SQLException e) {
            LOGGER.error("Update : {}", e.getMessage());
        }
    }
}
