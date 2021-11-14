package me.Mung.Commands.LostArk;

import me.Mung.Model.PlayerDAO;
import me.Mung.Model.PlayerVO;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static me.Mung.util.LACrawling.FindLevel;

public class SlashCommandMk implements SlashCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandMk.class);

    //  디스코드 사용자ID로 캐릭터를 DB에 등록시킨다.
    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        LOGGER.info(getClass().getSimpleName());
        final int FAIL = 0;
        PlayerVO user = new PlayerVO();
        user.setId_name(m.getId());
        user.setChar_name(event.getOption("character").getAsString());
        user.setCur_level(FindLevel(user.getChar_name()));
        if (user.getCur_level() == null) {
            LOGGER.error("Non User name");
            return;
        }
        if (PlayerDAO.insertPlayer(user) == FAIL) {
            LOGGER.error("Already exist");
            event.reply("이미 데이터에 있습니다.\n /rm명령어를 통해 제거하고 재등록하세요").setEphemeral(true).queue();
            return;
        }
        LOGGER.info("{}", user);
        StringBuffer s = new StringBuffer();
        s.append(m.getEffectiveName());
        s.append("에 ");
        s.append(user.getChar_name());
        s.append(" 등록");
        event.reply(String.valueOf(s)).setEphemeral(true).queue();
    }
}
