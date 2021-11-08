package me.Mung.Commands.LostArk;

import me.Mung.type.Command;
import me.Mung.util.SearchLevel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static me.Mung.util.DBConnection.ds;
import static me.Mung.util.DBConnection.rs;

public class CommandLs implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLs.class);

    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
//        LOGGER.info(getClass().getSimpleName());
        String[] cmd = message.getContentRaw().split(" ");

        LOGGER.info(m.getId());
        if (cmd.length > 1) {
            LOGGER.error("Undefined Command");
        }
        String sql = "select * from LA.user_id where id_name = ?";
        try {
            PreparedStatement pstmt = ds.getConnection().prepareStatement(sql);
            pstmt.setString(1, m.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                LOGGER.info("{} {} {}", rs.getString(1), rs.getString(2), rs.getInt(3));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
