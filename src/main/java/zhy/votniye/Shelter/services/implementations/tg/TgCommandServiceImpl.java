package zhy.votniye.Shelter.services.implementations.tg;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.services.interfaces.tg.TgBotService;
import zhy.votniye.Shelter.services.interfaces.tg.TgCommandService;
import zhy.votniye.Shelter.utils.tgUtility.TgMessageBuilder;

import java.util.EnumSet;

@Service
public class TgCommandServiceImpl implements TgCommandService {
    private final TelegramBot telegramBot;
    private final TgBotService botService;

    public TgCommandServiceImpl(TelegramBot telegramBot, TgBotService botService) {
        this.telegramBot = telegramBot;
        this.botService = botService;
    }

    @Override
    public void start(long chatId) {
        var buttons = botService.getAppropriateButtons(chatId);
        var message = TgMessageBuilder.getStartMessage(chatId, buttons);
        telegramBot.execute(message);
    }

    @Override
    public void startFresh(long chatId) {
        var message = TgMessageBuilder.getStartFreshMessage(chatId);
        telegramBot.execute(message);
    }
}
