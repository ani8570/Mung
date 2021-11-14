package me.Mung.type;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;

public interface SelectionMenuCommand {
    public void performCommand(SelectionMenuEvent event, Member m, TextChannel channel);
}
