package me.Mung.Commands.LostArk;

import me.Mung.type.Command;
import me.Mung.type.SlashCommand;
import me.Mung.util.LACrawling;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static me.Mung.util.Textformat.D2S;

public class SlashCommandFind implements SlashCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandFind.class);

    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        LOGGER.info(getClass().getSimpleName());
        String ChN = event.getOption("chn").getAsString();
        Double char_lv = LACrawling.FindLevel(ChN);
        LOGGER.info("{}", char_lv);
        if (char_lv == null) {
            LOGGER.error("Non User name");
            return;
        }
        LOGGER.info("{} : {} ", ChN, char_lv);
        event.reply(D2S(char_lv)).setEphemeral(true).queue();

    }
}
