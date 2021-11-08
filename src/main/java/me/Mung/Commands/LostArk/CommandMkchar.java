package me.Mung.Commands.LostArk;

import me.Mung.type.Command;
import me.Mung.util.SearchLevel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandMkchar implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandMkchar.class);

    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
//        LOGGER.info(getClass().getSimpleName());
        String[] cmd = message.getContentRaw().split(" ");

        Double char_lv = SearchLevel.FindLevel(cmd[1]);
        if(cmd.length != 2 || char_lv == null) {
            LOGGER.error("Non User name");
        }
        if (char_lv != null) {
            LOGGER.info("{} : {} ", cmd[1], char_lv);
            message.reply(Double.toString(char_lv)).queue();
        }
    }

}
