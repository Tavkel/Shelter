package zhy.votniye.Shelter.services.interfaces;

import com.pengrad.telegrambot.model.Message;

public interface TgBotService {
    void sayHello(long chatId);
    void about (Message message);
}
