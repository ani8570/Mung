package me.Mung.listener;

import me.Mung.Bot;
import me.Mung.Config;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        //봇 or 웹 후킹 차단
        if (event.getAuthor().isBot() || event.isWebhookMessage())
            return;
        //performCommand에 넣을 접두사 및 변수 선언
        String prefix = Config.get("prefix");
        Member m = event.getMember();
        TextChannel channel = event.getChannel();
        Message message = event.getMessage();
        //prefix로 시작하는 명령어가 있으면 실행
        if (message.getContentDisplay().startsWith(prefix)) {
            Bot.getCmdMan().performCommand(m, channel, message);
        }
    }
}
