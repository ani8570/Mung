package me.Mung.listener;

import me.Mung.Commands.CommandKVoice;
import me.Mung.Commands.CommandPing;
import me.Mung.Commands.LostArk.CommandHelp;
import me.Mung.Commands.LostArk.CommandLs;
import me.Mung.Commands.LostArk.CommandMk;
import me.Mung.Commands.LostArk.CommandRm;
import me.Mung.type.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CommandManager implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandManager.class);
    private final Map<String, Command> commands;

    //명령어 모음
    public CommandManager() {
        commands = new HashMap<>();
//        commands.put("ping", new CommandPing());
//        commands.put("kv", new CommandKVoice());
        commands.put("help", new CommandHelp());
        commands.put("", new CommandHelp());
        commands.put("등록", new CommandMk());
        commands.put("조회", new CommandLs());
        commands.put("제거", new CommandRm());
    }


    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        if (!channel.getId().equalsIgnoreCase("912180663749333063"))
            return;

        String cmd = message.getContentRaw().substring(1);
        Command command;
        if ((command = commands.get(cmd.toLowerCase())) != null) {
            command.performCommand(m, channel, message);
        } else {
            LOGGER.error("Error : Not exist command");
            message.delete().queueAfter(10, TimeUnit.SECONDS);
        }
    }
}
