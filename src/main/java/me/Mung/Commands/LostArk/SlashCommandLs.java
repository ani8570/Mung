package me.Mung.Commands.LostArk;

import me.Mung.Model.PlayerDAO;
import me.Mung.Model.PlayerVO;
import me.Mung.type.SlashCommand;
import me.Mung.util.LACrawling;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class SlashCommandLs implements SlashCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandLs.class);

    //  DB에 저장된 디스코드 사용자ID를 기반으로 캐릭터 목록 및 레벨을 가져온다.
    @Override
    public void performCommand(SlashCommandEvent event) {
        LOGGER.info(getClass().getSimpleName());
        OptionMapping char_name = event.getOption("character");
        String id = Objects.requireNonNull(event.getMember()).getId();
        StringBuffer replyMessage = new StringBuffer();
        // 이름이 없으면 아이디에 등록된 캐릭터들 최신화 목록 가져옴
        if (char_name == null) {
            List<PlayerVO> list = PlayerDAO.getCharList(id);
            if (list.size() == 0) {
                LOGGER.error("Not located player");
                return;
            }
            list.forEach(player -> {
                LOGGER.info("{}", player);
//                PlayerDAO.updatePlayer(player);
                replyMessage.append(String.format("%-10.2f\t", player.getCur_level()));
                replyMessage.append(player.getChar_name());
                replyMessage.append("\n");
            });
        }
        // 이름이 있으면 레벨 최신화 하고 캐릭터 가져옴
        else {
            PlayerVO player = new PlayerVO();
            player.setId_name(id);
            player.setChar_name(char_name.getAsString());
            player.setCur_level(LACrawling.FindLevel(player.getChar_name()));
            if (player.getCur_level() == null) {
                LOGGER.error("Non player char_name");
                return;
            }
            PlayerDAO.updatePlayer(player);
            LOGGER.info("{}", player);
            replyMessage.append(String.format("%-10.2f\t", player.getCur_level()));
            replyMessage.append(player.getChar_name());
        }
        event.reply(String.valueOf(replyMessage)).setEphemeral(true).queue();//m-> m.delete()queueAfter(20L, TimeUnit.SECONDS);
    }
}
