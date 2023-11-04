package zhy.votniye.Shelter.services.interfaces.tg;

import com.pengrad.telegrambot.model.Message;

public interface TgArgCommandService {
    void submitReport(Message message);
}
