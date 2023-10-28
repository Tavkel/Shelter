package zhy.votniye.Shelter.services.interfaces.tg;

import com.pengrad.telegrambot.model.Message;
import zhy.votniye.Shelter.models.enums.Status;
import zhy.votniye.Shelter.sessions.tg.TgSession;
import zhy.votniye.Shelter.utils.tgUtility.TgButton;

import java.util.Collection;
import java.util.EnumSet;

public interface TgBotService {
    void sendErrorReport(long chatId, String eMessage);

    EnumSet<TgButton> getAppropriateButtons(long chatId);

    //todo implement!
    Status.OwnerPreference getOwnerPreference(long chatId);

    Collection<Long> getSessionIds();

    TgSession getSession(long chatId);

    void leaveContact(Message message);

    void leaveContactStep(long chatId, int step);

    void dataIngestSessionFailure(long chatId, int step);
}
