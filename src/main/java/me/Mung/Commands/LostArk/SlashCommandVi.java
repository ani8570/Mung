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

import java.util.Arrays;
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
                .setActionRow(Button.success("vi:button:000", "숙제 관리 시작"), Button.success("vi:button:001", "숙제 조회"))
                .queue();
    }

    @Override
    public void performCommand(ButtonClickEvent event, Member m, TextChannel channel) {
        if (event.getComponentId().equals("vi:button:000") || event.getComponentId().equals("vi:button:001")) {
            playerList = PlayerDAO.getCharList(m.getId());
            if (playerList.isEmpty()) {
                event.reply("/mk를 통해 캐릭터 등록을 먼저해주세요.").setEphemeral(true).queue();
                return;
            }
            List<SelectOption> optionList = new LinkedList<>();
            optionList.add(SelectOption.of("원정대", "vi:selection:0"));
            playerList.forEach(player ->
                    optionList.add(SelectOption.of(player.getChar_name(), "vi:selection:" + player.getChar_name())));
            SelectionMenu menu = SelectionMenu.create("vi:menu:000")
                    .setPlaceholder("원정대 혹은 캐릭터를 선택해주세요.") // shows the placeholder indicating what this menu is for
                    .setRequiredRange(1, 1)
                    .addOptions(optionList)
                    .build();
            if (event.getComponentId().equals("vi:button:000"))
                event.reply(m.getUser().getAsTag()).addActionRow(menu).setEphemeral(true).queue();
            else {
                event.reply(m.getUser().getAsTag()).addActionRow(menu.createCopy().setId("vi:menu:200").build()).setEphemeral(true).queue();
            }

        } else if (event.getComponentId().equals("vi:button:101") || event.getComponentId().equals("vi:button:100")) {
            List<String> list = Arrays.asList(event.getMessage().getContentRaw().split("\\|"));
            List<String> homework = Arrays.asList(list.get(2).split(","));
            int cnt = event.getComponentId().equals("vi:button:101") ? 1 : 0;
            if (list.get(0).equalsIgnoreCase("원정대")) {
                IdHomeworkDAO.updateIdHomework(m.getId(), cnt, homework);
            } else {
                CharHomeworkDAO.updateCharHomework(list.get(1), cnt, homework);
            }
            LOGGER.info("{} {}", list.get(0), cnt);
            homework.forEach(s -> LOGGER.info("{}", s));
            event.editMessage("변경되었습니다.").setActionRows().queue();
        }
    }

    @Override
    public void performCommand(SelectionMenuEvent event, Member m, TextChannel channel) {
        if (event.getComponentId().equals("vi:menu:000")) {
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
            SelectionMenu menu = SelectionMenu.create("vi:menu:100")
                    .setPlaceholder("변경하고 싶은 모든 숙제들을 선택해주세요.") // shows the placeholder indicating what this menu is for
                    .setRequiredRange(1, L)
                    .addOptions(optionList)
                    .build();
            event.editMessage(event.getMessage().getContentRaw() + "|" + selectedoption).setActionRow(menu).queue();
        } else if (event.getComponentId().equals("vi:menu:100")) {
            List<String> list = new LinkedList<>();
            event.getSelectedOptions().forEach(selectOption -> list.add(selectOption.getLabel()));
            List<Button> buttonList = new LinkedList<>();
            buttonList.add(Button.success("vi:button:101", "\u2705"));
            buttonList.add(Button.danger("vi:button:100", "\u2B1C"));
            event.editMessage(event.getMessage().getContentRaw() + "|" + String.join(",", list)).setActionRow(buttonList).queue();
        } else if (event.getComponentId().equals("vi:menu:200")) {
            String selectedoption = Objects.requireNonNull(event.getSelectedOptions()).get(0).getLabel();
            event.editMessage(getHomework(selectedoption, m)).setActionRows().queue();
        }
    }

    private String getHomework(String selectedoption, Member m) {
        StringBuffer stringBuffer = new StringBuffer();

        if (selectedoption.equalsIgnoreCase("원정대")) {
            List<IdHomeworkVO> list = IdHomeworkDAO.getIdHomeworkList(m.getId());
            list.forEach(s -> {
                stringBuffer.append(String.format("%-" + (30 - s.getDungeon_name().getBytes().length) + "s", s.getDungeon_name()));
                stringBuffer.append(String.format("%-27s", s.getCnt() == 1 ? "\u2705" : "\u2B1C"));
                stringBuffer.append("\n\n");
            });
        } else {
            List<CharHomeworkVO> list = CharHomeworkDAO.getCharHomeworkList(selectedoption);
            list.forEach(s -> {
                stringBuffer.append(String.format("%-" + (30 - s.getDungeon_name().getBytes().length) + "s", s.getDungeon_name()));
                stringBuffer.append(String.format("%-27s", s.getCnt() == 1 ? "\u2705" : "\u2B1C"));
                stringBuffer.append("\n\n");
            });
        }
        return String.valueOf(stringBuffer);
    }
}
