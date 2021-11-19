package me.Mung.Commands.LostArk;

import me.Mung.Model.*;
import me.Mung.type.ButtonCommand;
import me.Mung.type.SelectionMenuCommand;
import me.Mung.type.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class SlashCommandVi implements SlashCommand, ButtonCommand, SelectionMenuCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlashCommandVi.class);
    List<PlayerVO> playerList;

    public static List<CharDungeonVO> charDungeonList;
    public static List<IdDungeonVO> idDungeonList;

    public SlashCommandVi() {
        charDungeonList = CharDungeonDAO.getCharDungeonList();
        idDungeonList = IdDungeonDAO.getIdDungeonList();
    }

    //  원정대 및 캐릭터 별 숙제 리스트 보여주기
    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        LOGGER.info(m.getId());
        LOGGER.info(m.getUser().getAsTag());
        LOGGER.info(m.getAsMention());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<:handup:910919406534664192> 안녕하세요!\n\n");
        stringBuffer.append("아래 숙제 관리시작 버튼을 클릭해주세요.\n\n");
        stringBuffer.append("버튼을 클릭하면 케릭터와 숙제를 선택할 수 있습니다.\n\n");
        stringBuffer.append("숙제를 선택하고 빈 곳을 클릭하면 다음 선택지로 이동됩니다.\n\n");
        stringBuffer.append("잘못 선택했을 경우, 메세지 닫기 클릭으로 취소할 수 있습니다.\n<:empty:910923324031402045>");
        channel.sendMessage(stringBuffer)
                .setActionRow(Button.success("vi:button:001", "숙제 관리 시작"))
                .queue();
    }

    @Override
    public void performCommand(ButtonClickEvent event, Member m, TextChannel channel) {
        if (event.getComponentId().equals("vi:button:001")) {
            playerList = PlayerDAO.getCharList(m.getId());
            if (playerList.isEmpty()) {
                event.reply("/mk를 통해 캐릭터 등록을 먼저해주세요.").setEphemeral(true).queue();
                return;
            }
            List<SelectOption> optionList = new LinkedList<>();
            optionList.add(SelectOption.of("원정대", "vi:selection:0"));
            playerList.forEach(player ->
                    optionList.add(SelectOption.of(player.getChar_name(), "vi:selection:" + player.getChar_name())));
            SelectionMenu menu = SelectionMenu.create("vi:menu:001")
                    .setPlaceholder("원정대 혹은 캐릭터를 선택해주세요.") // shows the placeholder indicating what this menu is for
                    .setRequiredRange(1, 1)
                    .addOptions(optionList)
                    .build();

            event.reply(m.getUser().getAsTag()).addActionRow(menu).setEphemeral(true).queue();
        }
    }

    @Override
    public void performCommand(SelectionMenuEvent event, Member m, TextChannel channel) {
        if (event.getComponentId().equals("vi:menu:001")) {
            String selectedoption = Objects.requireNonNull(event.getSelectedOptions()).get(0).getLabel();
            List<SelectOption> optionList = new LinkedList<>();
            int L = 1;
            if (selectedoption.equalsIgnoreCase("원정대")) {
                L = idDungeonList.size();
                idDungeonList.forEach(idDungeonVO ->
                        optionList.add(SelectOption.of(idDungeonVO.getDungeon_name(), "vi:selection:" + idDungeonVO.getDungeon_name())));
            } else {
                L = charDungeonList.size();
                charDungeonList.forEach(charDungeonVO ->
                        optionList.add(SelectOption.of(charDungeonVO.getDungeon_name(), "vi:selection:" + charDungeonVO.getDungeon_name())));
            }
            SelectionMenu menu = SelectionMenu.create("vi:menu:002")
                    .setPlaceholder("변경하고 싶은 모든 숙제들을 선택해주세요.") // shows the placeholder indicating what this menu is for
                    .setRequiredRange(1, L)
                    .addOptions(optionList)
                    .build();
            event.editMessage(event.getMessage().getContentRaw() + "|" + selectedoption).setActionRow(menu).queue();
        }
    }
}
