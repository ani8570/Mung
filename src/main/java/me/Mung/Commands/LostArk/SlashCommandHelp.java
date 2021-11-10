package me.Mung.Commands.LostArk;

import me.Mung.type.Command;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlashCommandHelp implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandHelp.class);

    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
//        LOGGER.info(getClass().getSimpleName());
        String[] cmd = message.getContentRaw().split(" ");

        if (cmd.length > 1) {
            LOGGER.error("Undefined Command");
            return;
        }
        StringBuffer s = new StringBuffer();
        s.append("!find 캐릭터명 : 캐릭터 Lv 조회\n");
        s.append("!ls : 등록한 내 모든 캐릭터 Lv 조회\n");
        s.append("!mk 캐릭터명 : 내 아이디에 캐릭터 등록\n");
        s.append("!rm 캐릭터명 : 모든 목록에서 캐릭터 삭제\n");
        message.reply(s).queue();
        LOGGER.info("Call help");
    }

}
