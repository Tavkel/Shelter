package zhy.votniye.Shelter.services.interfaces.tg;

import com.pengrad.telegrambot.model.Message;
import zhy.votniye.Shelter.sessions.tg.TgSession;

import java.util.Collection;

public interface TgBotService {
    void sendErrorReport(long chatId, String eMessage);

    Collection<Long> getSessionIds();

    TgSession getSession(long chatId);

    void sayHello(long chatId);

    //endregion
    //region double arg commands
    //endregion
    //region callbacks
    void callVolunteer(Message message);

    void about(Message message);

    void aboutGeneral(Message message);

    void aboutGettingToPet(Message message);

    void aboutTheDocumentsToReceive(Message message);

    void aboutPetTransportation(Message message);

    void aboutHomeImprovementForAPet(Message message);

    void aboutHomeImprovementForAYoungPet(Message message);

    void aboutHomeImprovementForADisabledPet(Message message);

    void aboutTheReasonsForRefusingToReceiveAPet(Message message);

    void aboutContacts(Message message);

    void aboutEntryPermit(Message message);

    void aboutRulesOnTerritory(Message message);

    void backToMain(Message message);

    void leaveContact(Message message);

    void leaveContactStep(long chatId, int step);

    void dataIngestSessionFailure(long chatId, int step);
    //endregion
}
