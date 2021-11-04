package me.Mung.Commands.LostArk;

import me.Mung.type.Command;
import me.Mung.util.LAinquiry;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandFind implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandFind.class);

    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
//        LOGGER.info(getClass().getSimpleName());
        String[] cmd = message.getContentRaw().split(" ");
        m.getId();

        Double char_lv = null;
        char_lv = LAinquiry.LVinquiry(cmd[1]);
        if (char_lv != null) {
            LOGGER.info("{} : {} ", cmd[1], char_lv);
            message.reply(Double.toString(char_lv)).queue();

        } else {
            LOGGER.error("Non User name");
        }

    }

}
