package me.Mung.Model;

import me.Mung.util.LACrawling;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static me.Mung.util.DBConnection.ds;
import static me.Mung.util.DBConnection.rs;

public class UserDAO {
    public static List<UserVO> getCharList(String id) {
        String sql = "select * from LA.user_table " +
                "where id_name = ?" +
                "order by cur_lv DESC";
        List<UserVO> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                UserVO user = new UserVO();
                user.setId_name(rs.getString(1));
                user.setChar_name(rs.getString(2));
                user.setCur_level(LACrawling.FindLevel(user.getChar_name()));
                updateUser(user);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void insertUser(UserVO user) {
        String sql = "insert into LA.user_table values(?,?,?)";
        try {
            PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getId_name());
            pstmt.setString(2, user.getChar_name());
            pstmt.setDouble(3, user.getCur_level());
            pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(UserVO user) {
        String sql = "delete LA.user_table where char_name = ?";
        try {
            PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getChar_name());
            pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(UserVO user) {
        String sql = "update LA.user_table set cur_lv = ? where char_name = ?";
        try {
            PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
            pstmt.setDouble(1, user.getCur_level());
            pstmt.setString(2, user.getChar_name());
            pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
