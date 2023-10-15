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
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.helpers.TgSession;
import zhy.votniye.Shelter.helpers.TgSessionTypes;
import zhy.votniye.Shelter.models.Owner;
import zhy.votniye.Shelter.services.interfaces.TgBotService;

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

    //region single arg commands
    @Override
    public void sayHello(long chatId) {
        SendMessage response = ResponseMessages.getHelloMessage(chatId);


        var result = telegramBot.execute(response);
//        var result = telegramBot.execute(response);

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

    @Override
    public void backToMain(Message message) {
        EditMessageText editText = new EditMessageText(message.chat().id(),
                message.messageId(),
                "Want something else?");

        EnumSet<Button> buttons = EnumSet.of(Button.ABOUT_SHELTER_BUTTON, Button.LEAVE_CONTACT_BUTTON,
                Button.SUBMIT_REPORT_BUTTON, Button.CALL_VOLUNTEER_BUTTON);//todo check for submit report availability needed
        var newKeyboard = assembleKeyboard(buttons);
        EditMessageReplyMarkup editButtons = new EditMessageReplyMarkup(message.chat().id(),
                message.messageId()).replyMarkup(newKeyboard);

        telegramBot.execute(editText);
        telegramBot.execute(editButtons);

    }

    @Override
    public void leaveContact(Message message) {
        cleanUpButtons(message);
        SendMessage response = new SendMessage(message.chat().id(), "i need your fio");
        telegramBot.execute(response);
        TgSession session = new TgSession(message.chat().id(), TgSessionTypes.LEAVE_CONTACT, this);

        session.setContactService(contactService);
        sessions.add(session);
    }

    @Override
    public void leaveContactStep(long chatId, int step) {
        if(step == 6){
            return;
        }
        SendMessage sendMessage = new SendMessage(chatId, "and now i need " + LeaveContactSteps.values()[step-1]);//todo rework
        telegramBot.execute(sendMessage);
    }

    private void cleanUpButtons(Message message) {
        EditMessageReplyMarkup edit = new EditMessageReplyMarkup(
                message.chat().id(),
                message.messageId()).replyMarkup(new InlineKeyboardMarkup());
        telegramBot.execute(edit);
    }

    //endregion
    private void sendMessage() {
    }

    private InlineKeyboardMarkup assembleKeyboard(EnumSet<Button> flags) {
        InlineKeyboardMarkup result = new InlineKeyboardMarkup();

        var flagsOrdered = flags.stream().sorted().toList();

        for (var b : flagsOrdered) {
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
        return sessions.stream().filter(s -> s.getChatId() == chatId).findFirst().get();
    }

    private static class ResponseMessages {
        private static SendMessage getHelloMessage(long chatId) {
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
                //Add status for owner
                //using bit flags determine if person was added to db (left contact before), if no add leave contact button
                //using bit flags determine if person already adopted an animal, if yes and is on probation period then
                //add report button
                Keyboard keyboard = new InlineKeyboardMarkup(Button.ABOUT_SHELTER_BUTTON.getButton())
                        .addRow(Button.LEAVE_CONTACT_BUTTON.getButton())
                        .addRow(Button.SUBMIT_REPORT_BUTTON.getButton())
                        .addRow(Button.CALL_VOLUNTEER_BUTTON.getButton());

                return result.replyMarkup(keyboard);
            }
        }

        private static String getAboutMessage() {
            return "What exactly I should tell you about our shelter?";
        }

        private static String getAboutGeneralMessage() {
            return "General info on the shelter. Address, working hours, etc";
        }

        private static String getAboutContactsMessage() {
            return "Contacts, phones, tg, whatsapp, link to web";
        }

        private static String getAboutEntryPermitMessage() {
            return "You need to request permit to enter shelter's territory. " +
                    "You can do it by calling following number: +*(***)***-**-**";
        }

        private static String getAboutRulesOnTerritoryMessage() {
            return "Keep quiet, no running, no smoking, etc, etc";
        }
    }

    private enum Button {
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
        ABOUT_PERMIT_BUTTON(new InlineKeyboardButton("Заказать пропуск на территорию")
                .callbackData("drive_permit")),
        ABOUT_RULES_ON_TERRITORY(new InlineKeyboardButton("Правила поведения на территории")
                .callbackData("rules_on_territory")),
        CALL_VOLUNTEER_BUTTON(new InlineKeyboardButton("Call volunteer")
                .callbackData("call_volunteer")),
        BACK_MAIN_BUTTON(new InlineKeyboardButton("<- Back <-")
                .callbackData("back_to_main"));
        private final InlineKeyboardButton button;

        Button(InlineKeyboardButton button) {
            this.button = button;
        }

        private InlineKeyboardButton getButton() {
            return this.button;
        }
    }
    private enum LeaveContactSteps {
        FIO (1), PHONE(2), ADDRESS(3), EMAIL(4), COMMENT(5), FINISH(6);
        private final int num;
        LeaveContactSteps(int step){
            num = step;
        }
    }
}
