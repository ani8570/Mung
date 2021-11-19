package me.Mung.Commands;

import me.Mung.type.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandPing implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandPing.class);

    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        LOGGER.info(m.getId());
        LOGGER.info(m.getUser().getAsTag());
        message.reply("Pong!").queue();
        channel.sendMessage(":ping_pong: " + m.getAsMention()).queue();
        m.getUser().openPrivateChannel().flatMap(c -> c.sendMessage("hello")).queue();
    }
}
