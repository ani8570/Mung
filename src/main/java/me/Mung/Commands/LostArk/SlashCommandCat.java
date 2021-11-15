package me.Mung.Commands.LostArk;

import me.Mung.Model.*;
import me.Mung.type.ButtonCommand;
import me.Mung.type.SlashCommand;
import me.Mung.util.LACrawling;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static me.Mung.util.Textformat.D2S;

public class SlashCommandCat implements SlashCommand, ButtonCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandCat.class);
    List<IdDungeonVO> idDungeonList;
    List<CharDungeonVO> charDungeonList;

    public SlashCommandCat() {

    }
    //  원정대 및 캐릭터 별 숙제 리스트 보여주기
    @Override
    public void performCommand(SlashCommandEvent event) {
        LOGGER.info(getClass().getSimpleName());
        OptionMapping char_name = event.getOption("character");
        StringBuffer replyMessage = new StringBuffer();
//      아이디에 해당된 전체 리스트를 StringBuffer로 모아 reply로 출력  
        if (char_name == null) {

        }
        event.reply(String.valueOf(replyMessage)).setEphemeral(true).queue();//m-> m.delete()queueAfter(20L, TimeUnit.SECONDS);

    }

    @Override
    public void performCommand(ButtonClickEvent event, Member m, TextChannel channel) {

    }
}
