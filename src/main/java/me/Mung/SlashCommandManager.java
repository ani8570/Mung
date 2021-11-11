package me.Mung;

import me.Mung.Commands.LostArk.*;
import me.Mung.Commands.SlashCommandHello;
import me.Mung.type.ButtonCommand;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static me.Mung.Bot.jda;

public class SlashCommandManager extends ListenerAdapter {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SlashCommandManager.class);
    private Map<String, SlashCommand> commandMap;
    private Map<String, ButtonCommand> buttonMap;

    public SlashCommandManager() {
        commandMap = new ConcurrentHashMap<>();
        commandMap.put("hello", new SlashCommandHello());
        commandMap.put("ls", new SlashCommandLs());
        commandMap.put("mk", new SlashCommandMk());
        commandMap.put("rm", new SlashCommandRm());
        commandMap.put("help", new SlashCommandHelp());

        jda.updateCommands().queue();
        jda.updateCommands()
                .addCommands(new CommandData("ls", "Find your Charactor")
                        .addOptions(new OptionData(OptionType.STRING, "character", "Nickname ")
                                .setRequired(false)))
                .addCommands(new CommandData("mk", "Insert your Charactor on your ID")
                        .addOptions(new OptionData(OptionType.STRING, "character", "Nickname ")
                                .setRequired(true)))
                .addCommands(new CommandData("rm", "Remove your Charactor")
                        .addOptions(new OptionData(OptionType.STRING, "character", "Nickname ")
                                .setRequired(true)))
                .addCommands(new CommandData("help", "Show all commands"))
                .queue();
        buttonMap = new ConcurrentHashMap<>();
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
        Button buttonName = event.getButton();
        ButtonCommand command;

        if (event.getComponentId().equals("hello")) {
            event.reply("Hello :)").queue(); // send a message in the channel
        } else if (event.getComponentId().equals("emoji")) {
            event.editMessage("That button didn't say click me").queue(); // update the message
        }
        if ((command = buttonMap.get(buttonName)) != null) {
            command.performCommand(event, event.getMember(), event.getTextChannel());
        }
    }
}
