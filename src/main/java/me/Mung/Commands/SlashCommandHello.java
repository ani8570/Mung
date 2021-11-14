package me.Mung.Commands;

import me.Mung.type.ButtonCommand;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlashCommandHello implements SlashCommand, ButtonCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandHello.class);

    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        LOGGER.info(getClass().getSimpleName());
        event.reply("Click the button to say hello").setEphemeral(true)
                .addActionRow(
                        Button.primary("H:hello", "Click Me"), // Button with only a label
                        Button.primary("H:emoji1", Emoji.fromMarkdown("<:minn:245267426227388416>")), // Button with only an emoji
//                        Button.primary("emoji2", Emoji.fromMarkdown("<:dog>")), // Button with only an emoji
                        Button.primary("H:emoji3", Emoji.fromMarkdown("<:minn:245267426227388416>"))) // Button with only an emoji

                .addActionRow(
                        Button.primary("H:hello1", "Click Me"), // Button with only a label
                        Button.primary("H:emoji4", Emoji.fromMarkdown("<:minn:245267426227388416>")), // Button with only an emoji
                        Button.primary("H:emoji5", Emoji.fromMarkdown("<:minn:245267426227388416>")) // Button with only an emoji
                )
                .queue();
    }

    @Override
    public void performCommand(ButtonClickEvent event, Member m, TextChannel channel) {


        if (event.getComponentId().equals("H:hello")) {
            event.editMessage("val 1").queue();
        } else if (event.getComponentId().equals("H:hello1")) {
            event.editMessage("va 0").queue();// send a message in the channel
        } else if (event.getComponentId().equals("H:emoji1")) {
            event.editMessage("That button didn't say click me").queue(); // update the message
        } else if (event.getComponentId().equals("H:emoj3")) {
            event.editMessage("3That button didn't say click me").queue(); // update the message
        } else if (event.getComponentId().equals("H:emoji4")) {
            event.editMessage("That4 button didn't say click me").queue(); // update the message
        } else if (event.getComponentId().equals("H:emoji5")) {
            event.deferEdit().queue(); // update the message
        }
    }
}
