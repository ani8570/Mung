package me.Mung.Commands.LostArk;

import me.Mung.Model.*;
import me.Mung.type.ButtonCommand;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlashCommandCat implements SlashCommand, ButtonCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandCat.class);
    List<IdDungeonVO> idDungeonList;
    List<CharDungeonVO> charDungeonList;
    List<IdHomeworkVO> idHomeworkList;
    Map<String, Map<String, Integer>> map;

    public SlashCommandCat() {
        idDungeonList = new ArrayList<>();
        charDungeonList = new ArrayList<>();
        idHomeworkList = new ArrayList<>();
    }

    //  원정대 및 캐릭터 별 숙제 리스트 보여주기
    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        LOGGER.info(m.getUser().getAsTag());
        OptionMapping char_name = event.getOption("character");
        StringBuffer replyMessage = new StringBuffer();
        idDungeonList = IdDungeonDAO.getIdDungeonList();
        charDungeonList = CharDungeonDAO.getCharDungeonList();
        map = new HashMap<>();

        //아이디에 해당된 전체 리스트를 StringBuffer로 모아 reply로 출력
        idHomeworkList = IdHomeworkDAO.getIdHomeworkList(m.getId());
        idHomeworkList.forEach(hwk -> {
            map.put(hwk.getId_name(), new HashMap<>()).put(hwk.getDungeon_name(), hwk.getCnt());
        });
        String s = idHomeworkList.get(1).getDungeon_name();

        event.reply(String.valueOf(replyMessage)).setEphemeral(true).queue();//m-> m.delete()queueAfter(20L, TimeUnit.SECONDS);
    }

    @Override
    public void performCommand(ButtonClickEvent event, Member m, TextChannel channel) {

    }
}
