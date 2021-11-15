package me.Mung.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static me.Mung.util.DBConnection.ds;
import static me.Mung.util.DBConnection.rs;

public class CharDungeonDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(CharDungeonDAO.class);

    public static List<CharDungeonVO> getCharDungeonList() {
        String sql = "select * from LA.char_DUNGEON order by cur_lv";
        List<CharDungeonVO> list = new ArrayList<>();
        try {
            Statement stmt = ds.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CharDungeonVO charDungeonVO = new CharDungeonVO();
                charDungeonVO.setCur_lv(rs.getDouble(1));
                charDungeonVO.setDungeon_name(rs.getString(2));
                charDungeonVO.setSort_num(rs.getInt(3));
                list.add(charDungeonVO);
            }
        } catch (SQLException e) {
            LOGGER.error("GetList : {}", e.getMessage());
        }
        return list;
    }
}
