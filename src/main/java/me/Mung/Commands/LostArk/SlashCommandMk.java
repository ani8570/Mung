package me.Mung.Commands.LostArk;

import me.Mung.Model.UserDAO;
import me.Mung.Model.UserVO;
import me.Mung.type.Command;
import me.Mung.type.SlashCommand;
import me.Mung.util.LACrawling;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlashCommandMk implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandMk.class);

    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
//        LOGGER.info(getClass().getSimpleName());
        String[] cmd = message.getContentRaw().split(" ");
        UserVO user = new UserVO();
        user.setId_name(m.getId());
        user.setChar_name(cmd[1]);
        user.setCur_level(LACrawling.FindLevel(cmd[1]));
        if (cmd.length != 2 || user.getCur_level() == null) {
            LOGGER.error("Non User name");
            return;
        }
        LOGGER.info("{}", user);
        UserDAO.insertUser(user);
        message.reply(m.getEffectiveName()+"에 " +user.getChar_name()+" 등록").queue();
    }
}
