package zhy.votniye.Shelter.services.interfaces;

import com.pengrad.telegrambot.model.Message;
import zhy.votniye.Shelter.helpers.TgSession;

import java.util.Collection;

public interface TgBotService {
    Collection<Long> getSessionIds();

    void sayHello(long chatId);

    void about(Message message);

    void aboutGeneral(Message message);

    void aboutContacts(Message message);

    void aboutEntryPermit(Message message);

    void aboutRulesOnTerritory(Message message);

    void backToMain(Message message);

    void leaveContact(Message message);
}
