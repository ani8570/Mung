package me.Mung.Commands;

import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.Button;

public class SlashCommandHello implements SlashCommand {
    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
//        event.reply(event.getOption("msg").getAsString()).queue();
        event.reply("Click the button to say hello").setEphemeral(true)
                .addActionRow(
                        Button.primary("hello", "Click Me"), // Button with only a label
                        Button.success("emoji1", Emoji.fromMarkdown("<:minn:245267426227388416>")), // Button with only an emoji
//                        Button.primary("emoji2", Emoji.fromMarkdown("<:dog>")), // Button with only an emoji
                        Button.success("emoji3", Emoji.fromMarkdown("<:minn:245267426227388416>"))) // Button with only an emoji
                .queue();
    }
}
