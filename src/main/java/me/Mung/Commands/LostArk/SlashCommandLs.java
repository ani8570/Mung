package me.Mung.Commands.LostArk;

import me.Mung.Model.UserDAO;
import me.Mung.Model.UserVO;
import me.Mung.type.Command;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static me.Mung.util.Textformat.D2S;

public class SlashCommandLs implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandLs.class);

    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
//        LOGGER.info(getClass().getSimpleName());
        String[] cmd = message.getContentRaw().split(" ");

        LOGGER.info(m.getId());
        if (cmd.length > 1) {
            LOGGER.error("Undefined Command");
            return;
        }
        List<UserVO> list = UserDAO.getCharList(m.getId());
        StringBuffer s = new StringBuffer();
        list.forEach(chr -> {
            LOGGER.info("{}", chr);
            s.append(D2S(chr.getCur_level()));
            s.append("    \t");
            s.append(chr.getChar_name());
            s.append("\n");
        });
        message.reply(s).queue();
    }

}
