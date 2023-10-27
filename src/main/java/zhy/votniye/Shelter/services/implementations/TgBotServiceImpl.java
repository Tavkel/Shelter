package zhy.votniye.Shelter.services.implementations;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.EditMessageReplyMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.sessions.tg.TgSession;
import zhy.votniye.Shelter.sessions.tg.TgSessionTypes;
import zhy.votniye.Shelter.models.domain.Owner;
import zhy.votniye.Shelter.services.interfaces.ContactService;
import zhy.votniye.Shelter.services.interfaces.TgBotService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class TgBotServiceImpl implements TgBotService {
    @Autowired
    private ContactService contactService;
    private final Logger logger = LoggerFactory.getLogger(TgBotServiceImpl.class);
    private final TelegramBot telegramBot;

    private final ArrayList<TgSession> sessions = new ArrayList<>();

    public TgBotServiceImpl(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    /**
     * Sends greeting message
     *
     * @param chatId
     */
    //region single arg commands
    @Override
    public void sayHello(long chatId) {
        SendMessage response = ResponseMessages.getHelloMessage(chatId);


        var result = telegramBot.execute(response);

        if (result.isOk()) {
            logger.debug("Response sent");
        } else {
            logger.warn("sayHello(). Failed to send response");
        }
    }

    //endregion
    //region double arg commands
    //endregion
    //region callbacks
    @Override
    public void callVolunteer(Message message) {
        SendMessage sendMessage = new SendMessage(message.chat().id(), "Called volunteer. Please await response, someone will reach out to you soon!");
        cleanUpButtons(message);
        telegramBot.execute(sendMessage);
        //Тут должна быть логика по которой чат айди юзера уходит в какой-то чатик волонтеров для обратной связи
        //или юзернейм
        //или контактные данные если юзер зарегестрирован
        //или что-то еще
    }

    /**
     * Edits bot's message from which callback was received into about menu form.
     *
     * @param message
     */
    @Override
    public void about(Message message) {
        EditMessageText editText = new EditMessageText(message.chat().id(),
                message.messageId(),
                ResponseMessages.getAboutMessage());

        EnumSet<Button> buttons = EnumSet.of(Button.ABOUT_GENERAL_BUTTON, Button.ABOUT_CONTACTS_BUTTON,
                Button.ABOUT_PERMIT_BUTTON, Button.ABOUT_RULES_ON_TERRITORY);
        var newKeyboard = assembleKeyboard(buttons);
        EditMessageReplyMarkup editButtons = new EditMessageReplyMarkup(message.chat().id(),
                message.messageId()).replyMarkup(newKeyboard);
        telegramBot.execute(editText);
        telegramBot.execute(editButtons);
    }

    /**
     * Edits bot's message from which callback was received into general info form.
     *
     * @param message
     */
    @Override
    public void aboutGeneral(Message message) {
        EditMessageText editText = new EditMessageText(message.chat().id(),
                message.messageId(),
                ResponseMessages.getAboutGeneralMessage());

        EnumSet<Button> buttons = EnumSet.of(Button.ABOUT_CONTACTS_BUTTON, Button.ABOUT_PERMIT_BUTTON,
                Button.ABOUT_RULES_ON_TERRITORY, Button.BACK_MAIN_BUTTON);
        var newKeyboard = assembleKeyboard(buttons);
        EditMessageReplyMarkup editButtons = new EditMessageReplyMarkup(message.chat().id(),
                message.messageId()).replyMarkup(newKeyboard);

        telegramBot.execute(editText);
        telegramBot.execute(editButtons);
    }

    /**
     * Edits bot's message from which callback was received into contacts info form.
     *
     * @param message
     */
    @Override
    public void aboutContacts(Message message) {
        EditMessageText editText = new EditMessageText(message.chat().id(),
                message.messageId(),
                ResponseMessages.getAboutContactsMessage());

        EnumSet<Button> buttons = EnumSet.of(Button.ABOUT_GENERAL_BUTTON, Button.ABOUT_PERMIT_BUTTON,
                Button.ABOUT_RULES_ON_TERRITORY, Button.BACK_MAIN_BUTTON);
        var newKeyboard = assembleKeyboard(buttons);
        EditMessageReplyMarkup editButtons = new EditMessageReplyMarkup(message.chat().id(),
                message.messageId()).replyMarkup(newKeyboard);

        telegramBot.execute(editText);
        telegramBot.execute(editButtons);
    }

    /**
     * Edits bot's message from which callback was received into drive-in permission info form.
     *
     * @param message
     */
    @Override
    public void aboutEntryPermit(Message message) {
        EditMessageText editText = new EditMessageText(message.chat().id(),
                message.messageId(),
                ResponseMessages.getAboutEntryPermitMessage());

        EnumSet<Button> buttons = EnumSet.of(Button.ABOUT_GENERAL_BUTTON, Button.ABOUT_CONTACTS_BUTTON,
                Button.ABOUT_RULES_ON_TERRITORY, Button.BACK_MAIN_BUTTON);
        var newKeyboard = assembleKeyboard(buttons);
        EditMessageReplyMarkup editButtons = new EditMessageReplyMarkup(message.chat().id(),
                message.messageId()).replyMarkup(newKeyboard);

        telegramBot.execute(editText);
        telegramBot.execute(editButtons);
    }

    /**
     * Edits bot's message from which callback was received into about rules on territory info form.
     *
     * @param message
     */
    @Override
    public void aboutRulesOnTerritory(Message message) {
        EditMessageText editText = new EditMessageText(message.chat().id(),
                message.messageId(),
                ResponseMessages.getAboutRulesOnTerritoryMessage());

        EnumSet<Button> buttons = EnumSet.of(Button.ABOUT_GENERAL_BUTTON, Button.ABOUT_CONTACTS_BUTTON,
                Button.ABOUT_PERMIT_BUTTON, Button.BACK_MAIN_BUTTON);
        var newKeyboard = assembleKeyboard(buttons);
        EditMessageReplyMarkup editButtons = new EditMessageReplyMarkup(message.chat().id(),
                message.messageId()).replyMarkup(newKeyboard);

        telegramBot.execute(editText);
        telegramBot.execute(editButtons);
    }

    /**
     * Edits bot's message from which callback was received into main menu form.
     *
     * @param message
     */
    @Override
    public void backToMain(Message message) {
        EditMessageText editText = new EditMessageText(message.chat().id(),
                message.messageId(),
                "Want something else?");

        EnumSet<Button> buttons = EnumSet.of(Button.ABOUT_SHELTER_BUTTON, Button.LEAVE_CONTACT_BUTTON,
                Button.SUBMIT_REPORT_BUTTON, Button.CALL_VOLUNTEER_BUTTON);//todo check for report submission availability needed
        var newKeyboard = assembleKeyboard(buttons);
        EditMessageReplyMarkup editButtons = new EditMessageReplyMarkup(message.chat().id(),
                message.messageId()).replyMarkup(newKeyboard);

        telegramBot.execute(editText);
        telegramBot.execute(editButtons);
    }

    /**
     * Starts process of collection {@link zhy.votniye.Shelter.models.domain.Contact} data
     *
     * @param message
     */
    @Override
    public void leaveContact(Message message) {
        //todo extract to method
        TgSession session = new TgSession(message.chat().id(), TgSessionTypes.LEAVE_CONTACT, this);
        session.setContactService(contactService);
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
            SendMessage sendMessage = new SendMessage(chatId, "Alright! All data written!");
            EnumSet<Button> buttons = EnumSet.of(Button.ABOUT_SHELTER_BUTTON, Button.LEAVE_CONTACT_BUTTON,
                    Button.SUBMIT_REPORT_BUTTON, Button.CALL_VOLUNTEER_BUTTON);
            var keyboard = assembleKeyboard(buttons);
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

    private void sendMessage() {
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
     * @param flags {@link Button}
     * @return created keyboard
     */
    private InlineKeyboardMarkup assembleKeyboard(EnumSet<Button> flags) {
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

    public static class ResponseMessages {

        //todo rework
        public static SendMessage getHelloMessage(long chatId) {
            SendMessage result;

            var owner = Optional.of(new Owner()); //get owner by chatId

            if (owner.isEmpty()) {
                result = new SendMessage(chatId, "Hello! This is SHELTER-NAME bot-assistant! " +
                        "Please choose which animal you'd like to adopt, or if you don't want to interact with me " +
                        "i can call one of the leather bags.");
                var dogButton = new InlineKeyboardButton("dog shelter").callbackData("dog_shelter");
                var volunteerButton = new InlineKeyboardButton("call volunteer").callbackData("call_volunteer");
                Keyboard keyboard = new InlineKeyboardMarkup(dogButton, volunteerButton);

                return result.replyMarkup(keyboard);
            } else {
                result = new SendMessage(chatId, "Hello! This is SHELTER-NAME bot-assistant! " +
                        "Please tell what i can do for you, or if you don't want to interact with me " +
                        "I can call one of the leather bags.");
                //todo
                // -using bit flags determine if person was added to db (left contact before), if no add leave contact button
                // -using bit flags determine if person already adopted an animal, if yes and is on probation period then
                // -add report button
                Keyboard keyboard = new InlineKeyboardMarkup(Button.ABOUT_SHELTER_BUTTON.getButton())
                        .addRow(Button.LEAVE_CONTACT_BUTTON.getButton())
                        .addRow(Button.SUBMIT_REPORT_BUTTON.getButton())
                        .addRow(Button.CALL_VOLUNTEER_BUTTON.getButton());

                return result.replyMarkup(keyboard);
            }
        }

        public static String getAboutMessage() {
            return "What exactly I should tell you about our shelter?";
        }

        public static String getAboutGeneralMessage() {
            return "General info on the shelter. Address, working hours, etc";
        }

        public static String getAboutContactsMessage() {
            return "Contacts, phones, tg, whatsapp, link to web";
        }

        public static String getAboutEntryPermitMessage() {
            return "You need to request permit to enter shelter's territory. " +
                    "You can do it by calling following number: +*(***)***-**-**";
        }

        public static String getAboutRulesOnTerritoryMessage() {
            return "Keep quiet, no running, no smoking, etc, etc";
        }
    }

    public enum Button {
        ABOUT_SHELTER_BUTTON(new InlineKeyboardButton("About shelter")
                .callbackData("about_shelter")),
        LEAVE_CONTACT_BUTTON(new InlineKeyboardButton("Leave contact")
                .callbackData("leave_contact")),
        SUBMIT_REPORT_BUTTON(new InlineKeyboardButton("Submit report")
                .callbackData("submit_report")),
        ABOUT_GENERAL_BUTTON(new InlineKeyboardButton("General information")
                .callbackData("general_info")),
        ABOUT_CONTACTS_BUTTON(new InlineKeyboardButton("Contacts")
                .callbackData("contacts")),
        ABOUT_PERMIT_BUTTON(new InlineKeyboardButton("Request permit to the territory")
                .callbackData("drive_permit")),
        ABOUT_RULES_ON_TERRITORY(new InlineKeyboardButton("Rules for visitors")
                .callbackData("rules_on_territory")),
        CALL_VOLUNTEER_BUTTON(new InlineKeyboardButton("Call volunteer")
                .callbackData("call_volunteer")),
        BACK_MAIN_BUTTON(new InlineKeyboardButton("<- Back <-")
                .callbackData("back_to_main"));
        private final InlineKeyboardButton button;

        Button(InlineKeyboardButton button) {
            this.button = button;
        }

        public InlineKeyboardButton getButton() {
            return this.button;
        }
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
