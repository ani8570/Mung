package me.Mung.Model;

import me.Mung.util.LACrawling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static me.Mung.util.DBConnection.ds;
import static me.Mung.util.DBConnection.rs;

public class IdDungeonDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdDungeonDAO.class);

    public static List<IdDungeonVO> getIdDungeonList() {
        String sql = "select * from LA.ID_DUNGEON " +
                "order by cur_lv DESC";
        List<IdDungeonVO> list = new ArrayList<>();
        try {
            Statement stmt = ds.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                IdDungeonVO dungeonVO = new IdDungeonVO();
                dungeonVO.setCur_lv(rs.getDouble(1));
                dungeonVO.setDungeon_name(rs.getString(2));
                list.add(dungeonVO);
            }
        } catch (SQLException e) {
            LOGGER.error("GetList : {}", e);
            e.printStackTrace();
        }
        return list;
    }
}
