package me.Mung.Commands.LostArk;

import me.Mung.Model.PlayerDAO;
import me.Mung.Model.PlayerVO;
import me.Mung.type.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static me.Mung.util.LACrawling.FindLevel;

public class CommandMk implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandMk.class);

    //  디스코드 사용자ID로 캐릭터를 DB에 등록시킨다.
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        LOGGER.info(m.getUser().getAsTag());
        final int FAIL = 0;
        String[] command = message.getContentRaw().split(" ");
        if (command.length != 2)
            return;
        PlayerVO user = new PlayerVO();
        user.setId_name(m.getId());
        user.setChar_name(command[1]);
        user.setCur_level(FindLevel(user.getChar_name()));

        StringBuilder s = new StringBuilder(m.getAsMention() + " ");
        s.append(user.getChar_name());
        if (user.getCur_level() == null) {
            LOGGER.error("Non User name");
            s.append("-> 없는 케릭터입니다.");
        } else if (PlayerDAO.insertPlayer(user) == FAIL) {
            LOGGER.error("Already exist");
            s.append("-> 이미 데이터에 있습니다.\n 명령어를 통해 제거하고 재등록하세요");
        } else {
            s.append("-> 목록에 등록");
        }
        message.reply(String.valueOf(s)).queue();
    }
}
