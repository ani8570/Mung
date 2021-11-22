package me.Mung.Commands.LostArk;

import me.Mung.type.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandHelp implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandHelp.class);

    //  모든 명령어를 reply형태로 보여준다.
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        LOGGER.info(m.getUser().getAsTag());
        String s = "/조회 (캐릭터명) : 등록한 내 모든(특정) 캐릭터 Lv 조회\n" +
                "/등록 캐릭터명 : 내 아이디에 캐릭터 등록\n" +
                "/제거 캐릭터명 : 모든 목록에서 캐릭터 제거";
        message.reply(s).queue();
    }
}
