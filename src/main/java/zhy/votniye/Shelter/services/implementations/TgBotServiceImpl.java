package zhy.votniye.Shelter.services.implementations;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.EditMessageReplyMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.models.Owner;
import zhy.votniye.Shelter.services.interfaces.TgBotService;

import java.util.Optional;


@Service
public class TgBotServiceImpl implements TgBotService {
    private final Logger logger = LoggerFactory.getLogger(TgBotServiceImpl.class);
    private final TelegramBot telegramBot;

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
        SendMessage response = ResponseMessages.getAboutMessage(message.chat().id());
        cleanUpButtons(message);
        telegramBot.execute(response);
    }

    @Override
    public void aboutGeneral(Message message) {
        SendMessage response = ResponseMessages.getAboutGeneralMessage(message.chat().id());
        //cleanUpButtons(message);
        telegramBot.execute(response);
    }

    @Override
    public void aboutContacts(Message message) {
        SendMessage response = ResponseMessages.getAboutContactsMessage(message.chat().id());
        //cleanUpButtons(message);
        telegramBot.execute(response);
    }

    @Override
    public void aboutEntryPermit(Message message) {
        SendMessage response = ResponseMessages.getAboutEntryPermitMessage(message.chat().id());
        //cleanUpButtons(message);
        telegramBot.execute(response);
    }

    @Override
    public void aboutRulesOnTerritory(Message message) {
        SendMessage response = ResponseMessages.getAboutRulesOnTerritoryMessage(message.chat().id());
        //cleanUpButtons(message);
        telegramBot.execute(response);
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
                Keyboard keyboard = new InlineKeyboardMarkup(Buttons.ABOUT_SHELTER_BUTTON)
                        .addRow(Buttons.LEAVE_CONTACT_BUTTON)
                        .addRow(Buttons.SUBMIT_REPORT_BUTTON)
                        .addRow(Buttons.CALL_VOLUNTEER_BUTTON);

                return result.replyMarkup(keyboard);
            }
        }

        private static SendMessage getAboutMessage(long chatId) {
            var result = new SendMessage(chatId, "What exactly I should tell you about our shelter?");
            var keyboard = new InlineKeyboardMarkup(Buttons.ABOUT_GENERAL_BUTTON)
                    .addRow(Buttons.ABOUT_CONTACTS_BUTTON)
                    .addRow(Buttons.ABOUT_PERMIT_BUTTON)
                    .addRow(Buttons.ABOUT_RULES_ON_TERRITORY);
            return result.replyMarkup(keyboard);
        }

        private static SendMessage getAboutGeneralMessage(long chatId) {
            return new SendMessage(chatId, "General info on the shelter. Address, working hours, etc");
        }

        private static SendMessage getAboutContactsMessage(long chatId) {
            return new SendMessage(chatId, "Contacts, phones, tg, whatsapp, link to web").
                    disableWebPagePreview(true);
        }

        private static SendMessage getAboutEntryPermitMessage(long chatId) {
            return new SendMessage(chatId, "You need to request permit to enter shelter's territory. " +
                    "You can do it by calling following number: +*(***)***-**-**");
        }

        private static SendMessage getAboutRulesOnTerritoryMessage(long chatId) {
            return new SendMessage(chatId, "Keep quiet, no running, no smoking, etc, etc");
        }
    }

    private static class Buttons {
        private static final InlineKeyboardButton SUBMIT_REPORT_BUTTON = new InlineKeyboardButton("Submit report")
                .callbackData("submit_report");
        private static final InlineKeyboardButton CALL_VOLUNTEER_BUTTON = new InlineKeyboardButton("Call volunteer")
                .callbackData("call_volunteer");
        private static final InlineKeyboardButton ABOUT_SHELTER_BUTTON = new InlineKeyboardButton("About shelter")
                .callbackData("about_shelter");
        private static final InlineKeyboardButton LEAVE_CONTACT_BUTTON = new InlineKeyboardButton("Leave contact")
                .callbackData("leave_contact");
        private static final InlineKeyboardButton ABOUT_GENERAL_BUTTON = new InlineKeyboardButton("General information")
                .callbackData("general_info");
        private static final InlineKeyboardButton ABOUT_CONTACTS_BUTTON = new InlineKeyboardButton("Contacts")
                .callbackData("contacts");
        private static final InlineKeyboardButton ABOUT_PERMIT_BUTTON = new InlineKeyboardButton("Заказать пропуск на территорию")
                .callbackData("drive_permit");
        private static final InlineKeyboardButton ABOUT_RULES_ON_TERRITORY = new InlineKeyboardButton("Правила поведения на территории")
                .callbackData("rules_on_territory");
    }
}
