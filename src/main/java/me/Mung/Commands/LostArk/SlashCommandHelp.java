package me.Mung.Commands.LostArk;

import me.Mung.type.Command;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SlashCommandHelp implements SlashCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandHelp.class);

    //  모든 명령어를 reply형태로 보여준다.
    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        LOGGER.info(getClass().getSimpleName());

        StringBuffer s = new StringBuffer();
        s.append("/ls (캐릭터명) : 등록한 내 모든(특정) 캐릭터 Lv 조회\n");
        s.append("/mk 캐릭터명 : 내 아이디에 캐릭터 등록\n");
        s.append("/rm 캐릭터명 : 모든 목록에서 캐릭터 삭제\n");
        event.reply(String.valueOf(s)).setEphemeral(true).queue();
        LOGGER.info("Call help");
    }

}
