package zhy.votniye.Shelter.services.interfaces.tg;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.scheduling.annotation.Scheduled;
import zhy.votniye.Shelter.models.domain.AdoptionProcessMonitor;
import zhy.votniye.Shelter.models.enums.Status;
import zhy.votniye.Shelter.sessions.tg.TgSession;
import zhy.votniye.Shelter.utils.tgUtility.TgButton;

import java.util.Collection;
import java.util.EnumSet;

public interface TgBotService {
    void sendErrorReport(long chatId, String eMessage);


    @Scheduled( cron = "0 20 * * *")
    void sendReportNotification();

    @Scheduled( cron = "0 21 * * * ")
    void sendReportScheduleWarning();

    EnumSet<TgButton> getAppropriateButtons(long chatId);

    //todo implement!
    Status.OwnerPreference getOwnerPreference(long chatId);

    Collection<Long> getSessionIds();

    TgSession getSession(long chatId);

    void leaveContact(Message message);

    void leaveContactStep(long chatId, int step);

    void dataIngestSessionFailure(long chatId, int step);
}
