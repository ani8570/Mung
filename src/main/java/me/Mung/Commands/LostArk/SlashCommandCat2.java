package me.Mung.Commands.LostArk;

import me.Mung.Model.*;
import me.Mung.type.ButtonCommand;
import me.Mung.type.SelectionMenuCommand;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SlashCommandCat2 implements SlashCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandCat2.class);

    List<PlayerVO> playerList;
    List<SelectOption> optionList;
    List<IdHomeworkVO> idHomeworkList;
    List<CharHomeworkVO> charHomeworkList;
    List<Button> buttonList;
    SelectionMenu menu;
    String char_name = null;

    //  원정대 및 캐릭터 별 숙제 리스트 보여주기
    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        LOGGER.info(m.getUser().getAsTag());
        init(m);
        StringBuffer replyMessage = new StringBuffer();
        event.reply("Click the button to say hello")
                .setEphemeral(true)

                .queue();

    }

    private void init(Member m) {
        playerList = PlayerDAO.getCharList(m.getId());
        optionList = new LinkedList<>();
        playerList.forEach(chr ->
                optionList.add(SelectOption.of(
                        chr.getChar_name(),
                        "cat2:" + chr.getChar_name())));
        menu = SelectionMenu.create("cat2:menu")
                .setPlaceholder("Choose your Character")
                .setRequiredRange(1, 1)
                .addOptions(optionList)
                .build();

    }

    @Override
    public void performCommand(ButtonClickEvent event, Member m, TextChannel channel) {

    }

    @Override
    public void performCommand(SelectionMenuEvent event, Member m, TextChannel channel) {
        char_name = event.getValues().get(1);
        buttonList = new LinkedList<>();
        charHomeworkList = CharHomeworkDAO.getCharHomeworkList(char_name);
        charHomeworkList.forEach(chrhwk -> {
            LOGGER.info("{}", chrhwk);
            buttonList.add(Button.primary("cat2:" + chrhwk.getDungeon_name(), chrhwk.getDungeon_name() + "\u2705"));
        });
        event.editMessage("Asd").setActionRow().queue();
//        event..addActionRow(buttonList).queue();
    }
}
