package me.Mung.Commands.LostArk;

import me.Mung.Model.PlayerDAO;
import me.Mung.Model.PlayerVO;
import me.Mung.type.Command;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class CommandRm implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandRm.class);

    //  해당 캐릭터를 DB에 삭제시킨다.
    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        LOGGER.info(m.getUser().getAsTag());
        String[] command = message.getContentRaw().split(" ");
        if (command.length != 2)
            return;
        PlayerVO user = new PlayerVO();
        user.setId_name(m.getId());
        user.setChar_name(command[1]);
        user.setCur_level(1.);
        PlayerDAO.deletePlayer(user);
        message.reply(m.getAsMention() + " " + user.getChar_name() + " 삭제").queue();
    }
}
