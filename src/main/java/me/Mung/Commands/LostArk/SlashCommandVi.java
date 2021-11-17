package me.Mung.Commands.LostArk;

import me.Mung.Model.CharHomeworkDAO;
import me.Mung.Model.IdHomeworkDAO;
import me.Mung.Model.IdHomeworkVO;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class SlashCommandVi implements SlashCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandVi.class);
    List<IdHomeworkVO> idHomeworkList;
    //던전      닉네임         회수
    Map<String, Map<String, Integer>> charHomeworkMap;

    private StringBuffer getHomeworkText(Member m) {
        StringBuffer replyMessage = new StringBuffer();
        //unicode emoji  : check box, empty box
        replyMessage.append(String.format("%s\n\u2705 : done!\n\u2B1C : raw!\n", m.getUser().getAsTag()));
        //IdDungeon name
        idHomeworkList.forEach(hwk -> {
            replyMessage.append(String.format("%-" + (32 - hwk.getDungeon_name().getBytes().length) + "s", hwk.getDungeon_name()));
        });
        //IdDungeon Done check
        replyMessage.append("\n");
        idHomeworkList.forEach(hwk -> {
            replyMessage.append(String.format("%-27s", hwk.getCnt() == 1 ? "\u2705" : "\u2B1C"));
        });
        replyMessage.append("\n\n");
        // 유사 테이블을 위한 공백
        replyMessage.append(String.format("%30s", ""));
        // 캐릭터 이름 목록
        charHomeworkMap.values().iterator().next().forEach((chr_name, n) -> {
            replyMessage.append(String.format("%-" + (30 - chr_name.getBytes().length) + "s", chr_name));
        });
        replyMessage.append("\n\n");
        //던전 및 던전 클리어 유무
        charHomeworkMap.forEach((name, chr_hwk) -> {
            replyMessage.append(String.format("%-" + (30 - name.getBytes().length) + "s", name));
            chr_hwk.forEach((chrDName, cnt) -> {
                replyMessage.append(String.format("%-27s", cnt == 1 ? "\u2705" : "\u2B1C"));
            });
            replyMessage.append("\n\n");
        });
        return replyMessage;
    }

    //  원정대 및 캐릭터 별 숙제 리스트 보여주기
    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        LOGGER.info(m.getUser().getAsTag());
        OptionMapping char_name = event.getOption("character");
        StringBuffer replyMessage = new StringBuffer();
        //아이디에 해당된 전체 리스트를 StringBuffer로 모아 reply로 출력
        idHomeworkList = IdHomeworkDAO.getIdHomeworkList(m.getId());
        charHomeworkMap = CharHomeworkDAO.getCharHomeworkMap(m.getId());
        assert idHomeworkList != null;
        assert charHomeworkMap != null;
        // 캐릭터 등록 여부 체크
        if (idHomeworkList.isEmpty() || charHomeworkMap.isEmpty()) {
            replyMessage.append("/mk를 통해 캐릭터를 먼저 등록해주세요.");
        } else {
            replyMessage = getHomeworkText(m);
        }
        LOGGER.info("{}", replyMessage.length());
        event.reply(String.valueOf(replyMessage)).setEphemeral(true).queue();
    }
}
