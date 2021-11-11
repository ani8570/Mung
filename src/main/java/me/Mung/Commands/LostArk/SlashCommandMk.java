package me.Mung.Commands.LostArk;

import me.Mung.Model.UserDAO;
import me.Mung.Model.UserVO;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static me.Mung.util.LACrawling.FindLevel;

public class SlashCommandMk implements SlashCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandMk.class);

    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        LOGGER.info(getClass().getSimpleName());
        UserVO user = new UserVO();
        user.setId_name(m.getId());
        user.setChar_name(event.getOption("character").getAsString());
        user.setCur_level(FindLevel(user.getChar_name()));
        if (user.getCur_level() == null) {
            LOGGER.error("Non User name");
            return;
        }
        if (UserDAO.insertUser(user) != 1) {
            LOGGER.error("Already exist");
            event.reply("이미 데이터에 있습니다.\n다시 확인해보시거나 /rm명령어를 통해 제거하세요").queue();
            return;
        }
        LOGGER.info("{}", user);
        StringBuffer s = new StringBuffer();
        s.append(m.getEffectiveName());
        s.append("에 ");
        s.append(user.getChar_name());
        s.append(" 등록");
        event.reply(String.valueOf(s))
                .setEphemeral(true).queue();
    }
}
