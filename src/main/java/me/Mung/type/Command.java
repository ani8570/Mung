package me.Mung.type;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public interface Command {
    public void performCommand(Member m, TextChannel channel, Message message);
}
