package me.Mung.listener;

import me.Mung.Commands.CommandKVoice;
import me.Mung.Commands.CommandPing;
import me.Mung.type.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CommandManager implements Command {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(CommandManager.class);
    private Map<String, Command> commands;

    //명령어 모음
    public CommandManager() {
        commands = new HashMap<String, Command>();
        commands.put("ping", new CommandPing());
        commands.put("kv", new CommandKVoice());

    }


    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        String cmd = message.getContentDisplay().split(" ")[0].substring(1);
        Command command;
        if ((command = commands.get(cmd.toLowerCase())) != null) {
            command.performCommand(m, channel, message);
        } else {
            LOGGER.error("Error : Not exist command");

        }
    }
}
