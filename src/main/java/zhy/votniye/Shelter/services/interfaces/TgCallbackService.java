package zhy.votniye.Shelter.services.interfaces;

import com.pengrad.telegrambot.model.Message;

public interface TgCallbackService {
    void callVolunteer(Message message);

    void about(Message message);

    void aboutGeneral(Message message);

    void aboutContacts(Message message);

    void aboutEntryPermit(Message message);

    void aboutRulesOnTerritory(Message message);

    void backToMain(Message message);

    void leaveContact(Message message);

    void leaveContactStep(long chatId, int step);

    void dataIngestSessionFailure(long chatId, int step);
}
