package zhy.votniye.Shelter.telegramBot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.services.interfaces.TgBotService;

import java.util.List;

@Service
public class TelegramBotUpdateListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdateListener.class);
    private final TelegramBot telegramBot;
    private final TgBotService botService;

    public TelegramBotUpdateListener(TelegramBot telegramBot, TgBotService botService) {
        this.telegramBot = telegramBot;
        this.botService = botService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            if (update.message().text().startsWith("/start")) {
                botService.sayHello(update.message().chat().id());
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
