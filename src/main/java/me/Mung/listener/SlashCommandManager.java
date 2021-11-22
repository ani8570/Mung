package me.Mung.listener;

import me.Mung.Commands.LostArk.*;
import me.Mung.type.ButtonCommand;
import me.Mung.type.SelectionMenuCommand;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static me.Mung.Bot.jda;

public class SlashCommandManager extends ListenerAdapter {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SlashCommandManager.class);
    //TODO edit SlashCommand interface
    private Map<String, SlashCommand> commandMap;

    public SlashCommandManager() {
        commandMap = new ConcurrentHashMap<>();

        commandMap.put("vi", new SlashCommandVi());

        jda.updateCommands().queue();
        jda.getGuildById("897348952603623464")
                .updateCommands()
                .addCommands(new CommandData("vi", "set env"))
                .addCommands(new CommandData("ls", "Find your Charactor")
                        .addOptions(new OptionData(OptionType.STRING, "character", "Nickname ")
                                .setRequired(false)))
                .queue();

    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        String commandName = event.getName();
        SlashCommand command;
        if ((command = commandMap.get(commandName)) != null) {
            command.performCommand(event, event.getMember(), event.getTextChannel());
        }
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        String buttonName = event.getComponentId().toLowerCase();
        ButtonCommand command;
        if ((command = commandMap.get(buttonName.split(":")[0])) != null) {
            command.performCommand(event, event.getMember(), event.getTextChannel());
        }
    }

    @Override
    public void onSelectionMenu(SelectionMenuEvent event) {
        String selectionName = event.getComponentId().toLowerCase();
        SelectionMenuCommand command;
        if ((command = commandMap.get(selectionName.split(":")[0])) != null) {
            command.performCommand(event, event.getMember(), event.getTextChannel());
        }
    }
}
