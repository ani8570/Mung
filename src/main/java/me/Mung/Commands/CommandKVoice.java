package me.Mung.Commands;

import me.Mung.type.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CommandKVoice implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandKVoice.class);
    //빌런 id 리스트.
    ArrayList<String> Villain;

    public CommandKVoice() {
        Villain = new ArrayList<>();
        //이지훈#2840
        Villain.add("788767702088810517");
        //이지훈#6683
        Villain.add("530042930732335113");
        //남궁다#1525
        Villain.add("247316124973137920");
    }

    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {
        String[] cmd = message.getContentRaw().split(" ");
        List<Member> L1 = new ArrayList<>();
        List<Member> L2 = channel.getMembers();
        try {
            if (cmd.length == 1) {

                Villain.forEach(s -> L1.add(m.getGuild().getMemberById(s)));
                L1.retainAll(L2);

                for (Member momber : L1) {
                    User user = momber.getUser();

                    channel.getGuild().kickVoiceMember(momber).queue();
                    LOGGER.info("KV : {}", user.getAsTag());

                }
            } else if (cmd.length == 2) {

                String user_name = cmd[1];
                Member M;
                if (user_name.equalsIgnoreCase("이지훈")) {
                    M = m.getGuild().getMemberById("788767702088810517");
                } else {
                    M = m.getGuild().getMemberByTag(user_name);
                }
                channel.getGuild().kickVoiceMember(M).queue();
            }
        } catch (Exception e) {
            LOGGER.error("Error : Not exist command");
        }
    }
}
