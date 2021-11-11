package me.Mung.Commands.LostArk;

import me.Mung.Model.UserDAO;
import me.Mung.Model.UserVO;
import me.Mung.type.SlashCommand;
import me.Mung.util.LACrawling;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static me.Mung.util.Textformat.D2S;

public class SlashCommandLs implements SlashCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandLs.class);

    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        LOGGER.info(getClass().getSimpleName());
        OptionMapping N = event.getOption("character");
        StringBuffer s = new StringBuffer();
        if (N == null) {
            List<UserVO> list = UserDAO.getCharList(m.getId());
            if (list.size() == 1) {
                LOGGER.error("Not located user");
                return;
            }
            list.forEach(chr -> {
                LOGGER.info("{}", chr);
                s.append(D2S(chr.getCur_level()));
                s.append("\t");
                s.append(chr.getChar_name());
                s.append("\n");
            });
        } else {
            UserVO user = new UserVO();
            user.setId_name(m.getId());
            user.setChar_name(N.getAsString());
            user.setCur_level(LACrawling.FindLevel(user.getChar_name()));
            String char_lv = D2S(user.getCur_level());
            if (char_lv == null) {
                LOGGER.error("Non User name");
                return;
            }
            UserDAO.updateUser(user);
            LOGGER.info("{}", user);
            s.append(char_lv);
            s.append("\t");
            s.append(user.getChar_name());
        }
        event.reply(String.valueOf(s)).setEphemeral(true).queue();//m-> m.delete()queueAfter(20L, TimeUnit.SECONDS);

    }
}
