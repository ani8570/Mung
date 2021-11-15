package me.Mung.Commands.LostArk;

import me.Mung.Model.PlayerDAO;
import me.Mung.Model.PlayerVO;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SlashCommandRm implements SlashCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandRm.class);

    //  해당 캐릭터를 DB에 삭제시킨다.
    @Override
    public void performCommand(SlashCommandEvent event) {
        LOGGER.info(getClass().getSimpleName());
        PlayerVO user = new PlayerVO(
                Objects.requireNonNull(event.getMember()).getId(),
                Objects.requireNonNull(event.getOption("character")).getAsString(),
                1.);
        LOGGER.info("{}", user);
        PlayerDAO.deletePlayer(user);
        event.reply(user.getChar_name() + " 삭제").setEphemeral(true).queue();
    }
}
