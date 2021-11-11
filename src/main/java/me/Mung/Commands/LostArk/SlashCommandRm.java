package me.Mung.Commands.LostArk;

import me.Mung.Model.UserDAO;
import me.Mung.Model.UserVO;
import me.Mung.type.Command;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlashCommandRm implements SlashCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandRm.class);

    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        LOGGER.info(getClass().getSimpleName());

        UserVO user = new UserVO();
        user.setId_name(m.getId());
        user.setChar_name(event.getOption("character").getAsString());
        LOGGER.info("{}", user);
        UserDAO.deleteUser(user);
        event.reply(user.getChar_name() + " 삭제")
                .setEphemeral(true).queue();
    }
}
