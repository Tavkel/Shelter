package zhy.votniye.Shelter.helpers;

import com.pengrad.telegrambot.model.Message;
import zhy.votniye.Shelter.services.interfaces.TgBotService;

import java.time.LocalDateTime;

public class TgSession {
    private final long chatId;

    private LocalDateTime lastUpdate;

    private int step;

    private final TgSessionTypes type;

    private final TgBotService botService;

    public TgSession(long chatId, TgSessionTypes type, TgBotService botService) {
        this.chatId = chatId;
        this.botService = botService;
        this.lastUpdate = LocalDateTime.now();
        this.type = type;
    }

    public long getChatId() {
        return chatId;
    }

    public void process(Message message) {
        botService.
        lastUpdate = LocalDateTime.now();
    }
}
