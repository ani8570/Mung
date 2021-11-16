package me.Mung.Commands.LostArk;

import lombok.extern.java.Log;
import me.Mung.Model.*;
import me.Mung.type.ButtonCommand;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.components.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class SlashCommandCat implements SlashCommand, ButtonCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandCat.class);
    List<IdHomeworkVO> idHomeworkList;
    //던전      닉네임         회수
    Map<String, Map<String, Integer>> charHomeworkMap;

    //  원정대 및 캐릭터 별 숙제 리스트 보여주기
    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        LOGGER.info(m.getUser().getAsTag());
        OptionMapping char_name = event.getOption("character");
        StringBuffer replyMessage = new StringBuffer();
        //아이디에 해당된 전체 리스트를 StringBuffer로 모아 reply로 출력
        idHomeworkList = IdHomeworkDAO.getIdHomeworkList(m.getId());
        replyMessage.append(String.format("%s\n\u2705 : done!\n\u2B1C : raw!\n", m.getUser().getAsTag()));
        idHomeworkList.forEach(hwk -> {
            replyMessage.append(String.format("%-" + (32 - hwk.getDungeon_name().getBytes().length) + "s", hwk.getDungeon_name()));
        });
        replyMessage.append("\n");
//        idHomeworkList.forEach(hwk -> {
//            replyMessage.append(String.format("%-30s", hwk.getCnt()));
//        });
//        replyMessage.append("\n");
        idHomeworkList.forEach(hwk -> {
            replyMessage.append(String.format("%-27s", hwk.getCnt() == 1 ? "\u2705" : "\u2B1C"));
        });
        replyMessage.append("\n\n");
        charHomeworkMap = CharHomeworkDAO.getCharHomeworkMap(m.getId());
        assert charHomeworkMap != null;

        replyMessage.append(String.format("%30s", ""));
        charHomeworkMap.values().iterator().next().forEach((chr_name, n) -> {
            replyMessage.append(String.format("%-" + (30 - chr_name.getBytes().length) + "s", chr_name));
        });
        replyMessage.append("\n\n");
        charHomeworkMap.forEach((name, chr_hwk) -> {
            replyMessage.append(String.format("%-" + (30 - name.getBytes().length) + "s", name));
            chr_hwk.forEach((chrDName, cnt) -> {
                replyMessage.append(String.format("%-27s", cnt == 1 ? "\u2705" : "\u2B1C"));
            });
            replyMessage.append("\n\n");
        });

        event.reply(String.valueOf(replyMessage))
                .setEphemeral(true).queue();//m-> m.delete()queueAfter(20L, TimeUnit.SECONDS);

    }

    @Override
    public void performCommand(ButtonClickEvent event, Member m, TextChannel channel) {

    }
}
