package zhy.votniye.Shelter.services.implementations.tg;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.EditMessageReplyMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.exceptions.GetOwnerPreferenceException;
import zhy.votniye.Shelter.models.domain.Owner;
import zhy.votniye.Shelter.models.domain.UnregisteredOwner;
import zhy.votniye.Shelter.models.enums.Status;
import zhy.votniye.Shelter.services.interfaces.OwnerService;
import zhy.votniye.Shelter.services.interfaces.UnregisteredOwnerService;
import zhy.votniye.Shelter.sessions.tg.TgSession;
import zhy.votniye.Shelter.sessions.tg.TgSessionTypes;
import zhy.votniye.Shelter.services.interfaces.tg.TgBotService;
import zhy.votniye.Shelter.utils.tgUtility.TgButton;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class TgBotServiceImpl implements TgBotService {
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private UnregisteredOwnerService unregisteredOwnerService;
    private final Logger logger = LoggerFactory.getLogger(TgBotServiceImpl.class);
    private final TelegramBot telegramBot;
    private final ArrayList<TgSession> sessions = new ArrayList<>();

    public TgBotServiceImpl(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    /**
     * Starts process of collection {@link zhy.votniye.Shelter.models.domain.Contact} data
     *
     * @param message
     */
    @Override
    public void leaveContact(Message message) {
        TgSession session = new TgSession(message, TgSessionTypes.LEAVE_CONTACT, this);
        session.setContactService(ownerService);
        sessions.add(session);

        cleanUpButtons(message);
        SendMessage response = new SendMessage(message.chat().id(), "First I need your last name");
        telegramBot.execute(response);
    }

    /**
     * Sends message to user asking for next piece of data for next step
     * Sends "success" message on last step and dereferences {@link TgSession}
     *
     * @param chatId
     * @param step
     */
    @Override
    public void leaveContactStep(long chatId, int step) {
        if (step == 8) {
            var toRemove = sessions.stream().filter(s -> s.getChatId() == chatId).findFirst().get();
            toRemove.destroy();
            sessions.remove(toRemove);
            SendMessage sendMessage = new SendMessage(chatId, "Alright! All data written! " +
                    "Our colleagues will contact you shortly to verify provided data!");

            var keyboard = assembleKeyboard(EnumSet.of(TgButton.ABOUT_SHELTER_BUTTON, TgButton.CALL_VOLUNTEER_BUTTON));
            sendMessage.replyMarkup(keyboard);
            telegramBot.execute(sendMessage);
            return;
        }
        SendMessage sendMessage = new SendMessage(chatId, "and now i need " + LeaveContactSteps.values()[step - 1]);//todo rework
        telegramBot.execute(sendMessage);
    }

    /**
     * Notifies user of failure while processing provided data on current step
     *
     * @param chatId
     * @param step
     */
    @Override
    public void dataIngestSessionFailure(long chatId, int step) {
        SendMessage sendMessage = new SendMessage(chatId, "Failed to process data " + LeaveContactSteps.values()[step - 1]);
        telegramBot.execute(sendMessage);
    }

    //endregion

    /**
     * removes buttons from message sending callback
     *
     * @param message
     */
    private void cleanUpButtons(Message message) {
        EditMessageReplyMarkup edit = new EditMessageReplyMarkup(
                message.chat().id(),
                message.messageId()).replyMarkup(new InlineKeyboardMarkup());
        telegramBot.execute(edit);
    }

    /**
     * Basic error message sender
     *
     * @param chatId
     * @param eMessage
     */
    @Override
    public void sendErrorReport(long chatId, String eMessage) {
        var sendMessage = new SendMessage(chatId, "Encountered error while processing your message:\n" + eMessage);
        telegramBot.execute(sendMessage);
    }

    //todo ОБРАТИТЬ ВНИМАНИЕ НА МЕТОД getByChatId
    @Override
    public EnumSet<TgButton> getAppropriateButtons(long chatId) {
        EnumSet<TgButton> result = EnumSet.of(TgButton.ABOUT_SHELTER_BUTTON, TgButton.CALL_VOLUNTEER_BUTTON);
        Optional<Owner> oOwner = ownerService.getByChatId(chatId);
        if (oOwner.isEmpty()) {
            result.add(TgButton.LEAVE_CONTACT_BUTTON);
        }
        return result;
    }


    @Override
    public Status.OwnerPreference getOwnerPreference(long chatId) {

        Optional<Owner> owner = ownerService.getByChatId(chatId);

        Optional<UnregisteredOwner> unregOwner = unregisteredOwnerService.read(chatId);

        if(owner.isPresent()){
            return owner.get().getPreference();
        }else if(unregOwner.isPresent()){
            return unregOwner.get().getPreference();
        }else {
            return Status.OwnerPreference.NOT_FOUND;
        }

    }

    /**
     * Dereferences any {@link TgSession} longer than 2 minutes
     */
    @Scheduled(cron = "0 0/1 * * * *")
    private void searchAndDestroySessions() {
        sessions.forEach(session -> {
            var lastUpdate = session.getLastUpdate();
            var timePassed = Duration.between(lastUpdate, LocalDateTime.now()).toMinutesPart();


            //extract session life duration limit to props?
            if (timePassed > 2) {
                var chatId = session.getChatId();
                session.destroy();
                sessions.remove(session);
                telegramBot.execute(new SendMessage(chatId, "Closing session. Reason - reached time limit"));
            }
        });
    }

    /**
     * Builds keyboard based on provided flags
     *
     * @param flags {@link TgButton}
     * @return created keyboard
     */
    private InlineKeyboardMarkup assembleKeyboard(EnumSet<TgButton> flags) {
        InlineKeyboardMarkup result = new InlineKeyboardMarkup();

        for (var b : flags) {
            result.addRow(b.getButton());
        }
        return result;
    }

    @Override
    public List<Long> getSessionIds() {
        return sessions.stream().map(TgSession::getChatId).toList();
    }

    @Override
    public TgSession getSession(long chatId) {
        return sessions.stream().filter(s -> s.getChatId() == chatId).findFirst().orElseThrow(NoSuchElementException::new);
    }

    public enum LeaveContactSteps {
        //todo add associated message to step
        LAST_NAME(1), FIRST_NAME(2), MIDDLE_NAME(3), PHONE(4), ADDRESS(5), EMAIL(6), COMMENT(7), FINISH(8), FAILURE(9);
        private final int num;

        LeaveContactSteps(int step) {
            num = step;
        }

        public int getStep() {
            return this.num;
        }
    }
}
