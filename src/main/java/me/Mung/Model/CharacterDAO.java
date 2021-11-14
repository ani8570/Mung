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

public class CharacterDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterDAO.class);
    List<CharacterVO> getCharList(String id) {
        String sql = "select * from LA.char_check " +
                "where id_name = ?" +
                "order by cur_lv DESC";
        List<CharacterVO> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CharacterVO character = new CharacterVO();
                character.setChar_name(rs.getString(1));
                character.setDungeon_name(rs.getString(2));
                character.setCheck_num(rs.getInt(3));
                character.setSort_num(rs.getInt(4));
                list.add(character);
            }
        } catch (SQLException e) {
            LOGGER.error("GetList : {}", e);
            e.printStackTrace();
        }
        return list;
    }

    CharacterVO getCharacter(String name) {
        String sql = "select * from LA.char_check " +
                "where char_name = ?";
        try {
            PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                CharacterVO character = new CharacterVO();
                character.setChar_name(rs.getString(1));
                character.setDungeon_name(rs.getString(2));
                character.setCheck_num(rs.getInt(3));
                character.setSort_num(rs.getInt(4));
            }
        } catch (SQLException e) {
            LOGGER.error("GetList : {}", e);
            e.printStackTrace();
        }
        return null;
    }
}
