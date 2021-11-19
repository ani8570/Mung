package me.Mung.type;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;

public interface ButtonCommand{
    public void performCommand(ButtonClickEvent event, Member m, TextChannel channel);
}
