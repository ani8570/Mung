package me.Mung.Domain;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static me.Mung.util.DBConnection.ds;
import static me.Mung.util.DBConnection.rs;

public class UserDAO {
    List<UserVO> getCharList(String id) {
        String sql = "select * from LA.user_id where id_name = ?";
        List<UserVO>
        try {
            PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void setUser(UserVO user) {

    }
}
