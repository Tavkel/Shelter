package zhy.votniye.Shelter.services.implementations.tg;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.services.interfaces.tg.TgCommandService;
import zhy.votniye.Shelter.utils.tgUtility.TgButton;
import zhy.votniye.Shelter.utils.tgUtility.TgMessageBuilder;

import java.util.EnumSet;

@Service
public class TgCommandServiceImpl implements TgCommandService {
    private final TelegramBot telegramBot;

    public TgCommandServiceImpl(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void start(long chatId) {
        EnumSet<TgButton> buttons = EnumSet.of(TgButton.ABOUT_SHELTER_BUTTON, TgButton.CALL_VOLUNTEER_BUTTON);
        //todo
        // -button set logic
        // -if user has pet on prob period - add submit report
        // -if user not registered - add leave contact
        var message = TgMessageBuilder.getStartMessage(chatId, buttons);
        telegramBot.execute(message);
    }

    @Override
    public void startFresh(long chatId) {
        var message = TgMessageBuilder.getStartFreshMessage(chatId);
        telegramBot.execute(message);
    }
}
