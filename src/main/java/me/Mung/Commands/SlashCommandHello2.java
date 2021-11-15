package me.Mung.Commands;

import me.Mung.Model.CharDungeonDAO;
import me.Mung.Model.CharDungeonVO;
import me.Mung.Model.IdDungeonDAO;
import me.Mung.Model.IdDungeonVO;
import me.Mung.type.ButtonCommand;
import me.Mung.type.SelectionMenuCommand;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class SlashCommandHello2 implements SlashCommand, ButtonCommand, SelectionMenuCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandHello2.class);
    List<SelectOption> optionList;
    SelectionMenu menu;

    List<Button> buttonList;
    List<IdDungeonVO> idDungeonList;
    List<CharDungeonVO> charDungeonList;
    boolean Flag = true;

    public SlashCommandHello2() {
        buttonList = new ArrayList<>();
        buttonList.add(Button.primary("H2:100", "1"));
        buttonList.add(Button.primary("H2:101", "2"));
        buttonList.add(Button.primary("H2:102", "3"));
        buttonList.add(Button.primary("H2:103", "4"));

        optionList = new ArrayList<>();
        charDungeonList = CharDungeonDAO.getCharDungeonList();
        idDungeonList = IdDungeonDAO.getIdDungeonList();
        for (int i = 0; i < idDungeonList.size(); i++) {
            optionList.add(SelectOption.of(idDungeonList.get(i).getDungeon_name(), "H2:" + i));
        }
        menu = SelectionMenu.create("H2:class")
                .setPlaceholder("Choose your Homework") // shows the placeholder indicating what this menu is for
                .setRequiredRange(1, 1)
                .addOptions(optionList)
                .build();

    }

    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        LOGGER.info(getClass().getSimpleName());
        event.reply("Click the button to say hello").setEphemeral(true)
                .addActionRow(menu)
                .addActionRow(buttonList)
                .addActionRow(
                        Button.primary("H2:switch", "Click Me") // Button with only a label
//                        Button.danger("H2:off", "Click Me") // Button with only an emoji
                )
                .queue();

    }

    @Override
    public void performCommand(ButtonClickEvent event, Member m, TextChannel channel) {

        if (event.getComponentId().equals("H2:switch")) {
            if (!Flag) {
                event.editMessage("val 1").queue();
                event.editButton(Button.primary(event.getComponentId(), Emoji.fromUnicode("U+2705"))).complete();
            } else {
                event.editMessage("val 0").queue();
                event.editButton(Button.danger(event.getComponentId(), Emoji.fromUnicode("U+274E"))).complete();
            }
            Flag = !Flag;
        }
    }

    @Override
    public void performCommand(SelectionMenuEvent event, Member m, TextChannel channel) {
        LOGGER.info("_____________________");
        LOGGER.info("1 {}", event.getComponent());
        LOGGER.info("2 {}", event.getValues());
        LOGGER.info("_____________________");
    }
}
