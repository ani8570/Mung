package me.Mung.listener;

import me.Mung.Commands.LostArk.*;
import me.Mung.Commands.SlashCommandHello;
import me.Mung.Commands.SlashCommandHello2;
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
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static me.Mung.Bot.jda;

public class SlashCommandManager extends ListenerAdapter {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SlashCommandManager.class);
    private Map<String, SlashCommand> commandMap;
    private Map<String, ButtonCommand> buttonMap;
    private Map<String, SelectionMenuCommand> selectionMap;

    public SlashCommandManager() {
        commandMap = new ConcurrentHashMap<>();
        commandMap.put("hello", new SlashCommandHello());
        commandMap.put("ls", new SlashCommandLs());
        commandMap.put("mk", new SlashCommandMk());
        commandMap.put("rm", new SlashCommandRm());
        commandMap.put("cat", new SlashCommandCat());
        commandMap.put("help", new SlashCommandHelp());
        commandMap.put("hello2", new SlashCommandHello2());

        buttonMap = new ConcurrentHashMap<>();
        buttonMap.put("H", new SlashCommandHello());
        buttonMap.put("H2", new SlashCommandHello2());

        selectionMap = new ConcurrentHashMap<>();
        selectionMap.put("H2", new SlashCommandHello2());

        jda.updateCommands().queue();

        jda.getGuildById("897348952603623464")
                .updateCommands()
                .addCommands(new CommandData("ls", "Find your Charactor")
                        .addOptions(new OptionData(OptionType.STRING, "character", "Nickname ")
                                .setRequired(false)))
                .addCommands(new CommandData("mk", "Insert your Charactor on your ID")
                        .addOptions(new OptionData(OptionType.STRING, "character", "Nickname ")
                                .setRequired(true)))
                .addCommands(new CommandData("rm", "Remove your Charactor")
                        .addOptions(new OptionData(OptionType.STRING, "character", "Nickname ")
                                .setRequired(true)))
                .addCommands(new CommandData("cat", "Show your Homework")
                        .addOptions(new OptionData(OptionType.STRING, "character", "Nickname ")
                                .setRequired(false)))
                .addCommands(new CommandData("help", "Show all commands"))
                .addCommands(new CommandData("hello", "Button Test"))
                .addCommands(new CommandData("hello2", "Selection Tese"))

                .queue();


    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        String commandName = event.getName();
        SlashCommand command;
        if ((command = commandMap.get(commandName)) != null) {
            command.performCommand(event);
        }
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        String buttonName = event.getComponentId();

        ButtonCommand command;
        LOGGER.info("{}", buttonName);

        if ((command = buttonMap.get(buttonName.split(":")[0])) != null) {
            command.performCommand(event, event.getMember(), event.getTextChannel());
        }
    }

    @Override
    public void onSelectionMenu(@NotNull SelectionMenuEvent event) {
        String selectionName = event.getComponentId();

        SelectionMenuCommand command;
        LOGGER.info("{}", selectionName);

        if ((command = selectionMap.get(selectionName.split(":")[0])) != null) {
            command.performCommand(event, event.getMember(), event.getTextChannel());
        }
    }
}
