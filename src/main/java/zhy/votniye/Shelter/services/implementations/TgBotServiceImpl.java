package zhy.votniye.Shelter.services.implementations;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.services.interfaces.TgBotService;

@Service
public class TgBotServiceImpl implements TgBotService {
    private final Logger logger = LoggerFactory.getLogger(TgBotServiceImpl.class);
    private final TelegramBot telegramBot;

    public TgBotServiceImpl(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void sayHello(long chatId) {
        SendMessage response = new SendMessage(chatId, "Hello");
        var result = telegramBot.execute(response);

        if (result.isOk()) {
            logger.debug("Response sent");
        } else {
            logger.warn("sayHello(). Failed to send response");
        }
    }
}
