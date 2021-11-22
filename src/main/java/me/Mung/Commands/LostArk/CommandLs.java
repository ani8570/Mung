package me.Mung.Commands.LostArk;

import me.Mung.Model.PlayerDAO;
import me.Mung.Model.PlayerVO;
import me.Mung.type.Command;
import me.Mung.util.LACrawling;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CommandLs implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLs.class);

    //  DB에 저장된 디스코드 사용자ID를 기반으로 캐릭터 목록 및 레벨을 가져온다.
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        LOGGER.info(m.getUser().getAsTag());
        String[] command = message.getContentRaw().split(" ");
        if (command.length > 2)
            return;

        StringBuffer replyMessage = new StringBuffer();
        // 이름이 없으면 아이디에 등록된 캐릭터들 최신화 목록 가져옴
        if (command.length == 1) {
            List<PlayerVO> list = PlayerDAO.getCharList(m.getId());
            if (list.isEmpty()) {
                replyMessage.append("/mk로 등록을 먼저하세요");
                LOGGER.error("Not located player");
            } else {
                list.forEach(player -> {
                    replyMessage.append(String.format("%-10.2f\t", player.getCur_level()));
                    replyMessage.append(player.getChar_name());
                    replyMessage.append("\n");
                });
            }
        }
        // 이름이 있으면 레벨 최신화 하고 캐릭터 가져옴
        else {
            PlayerVO player = new PlayerVO();
            player.setId_name(m.getId());
            player.setChar_name(command[1]);
            player.setCur_level(LACrawling.FindLevel(player.getChar_name()));
            if (player.getCur_level() == null) {
                LOGGER.error("Non player char_name");
                return;
            }
            PlayerDAO.updatePlayer(player);
            replyMessage.append(String.format("%-10.2f\t", player.getCur_level()));
            replyMessage.append(player.getChar_name());
        }
        message.reply(String.valueOf(replyMessage)).queue();

    }
}
