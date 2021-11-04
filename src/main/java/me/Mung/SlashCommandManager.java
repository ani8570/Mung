package me.Mung;

import me.Mung.Commands.SlashCommandHello;
import me.Mung.type.Command;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static me.Mung.Bot.jda;

public class SlashCommandManager extends ListenerAdapter {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SlashCommandManager.class);
    private Map<String, SlashCommand> commandMap;

    public SlashCommandManager() {
        commandMap = new ConcurrentHashMap<>();
        CommandListUpdateAction commands = jda.updateCommands();
        commandMap.put("hello", new SlashCommandHello());
        commands.addCommands(new CommandData("hello", "hello")
                .addOptions(new OptionData(OptionType.STRING, "msg", "The message to resend.").setRequired(true)));

        jda.upsertCommand("ping", "Calculate ping of the bot").queue(); // This can take up to 1 hour to show up in the client
//        jda.upsertCommand("hello", "hello").queue(); // This can take up to 1 hour to show up in the client
        jda.upsertCommand("info", "info").queue(); // This can take up to 1 hour to show up in the client
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        LOGGER.info("onSlashCommand");
        String commandName = event.getName();
        SlashCommand command;
        if ((command = commandMap.get(commandName)) != null) {
            command.performCommand(event, event.getMember(), event.getTextChannel());

        }
    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        LOGGER.info("onButtonClick");
        if (event.getComponentId().equals("hello")) {
            event.reply("Hello :)").queue(); // send a message in the channel
        } else if (event.getComponentId().equals("emoji")) {
            event.editMessage("That button didn't say click me").queue(); // update the message
        }
    }
}
