package zhy.votniye.Shelter.utils.tgUtility;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.EditMessageReplyMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import zhy.votniye.Shelter.config.InfoStrings;
import zhy.votniye.Shelter.models.enums.Status;

import java.util.EnumSet;

import static zhy.votniye.Shelter.config.InfoStrings.BotMessageTextProvider.*;

public class TgMessageBuilder {
    public static SendMessage getStartMessage(long chatId, EnumSet<TgButton> buttons) {
        SendMessage result = new SendMessage(chatId, getHelloMessageForReturningUser());
        Keyboard keyboard = assembleKeyboard(buttons);
        result.replyMarkup(keyboard);
        return result;
    }

    public static SendMessage getStartFreshMessage(long chatId) {
        SendMessage result = new SendMessage(chatId, getHelloMessageForNewUser());
        var keyboard = assembleKeyboard(EnumSet.of(TgButton.CAT_SHELTER_BUTTON, TgButton.DOG_SHELTER_BUTTON));
        result.replyMarkup(keyboard);
        return result;
    }

    public static SendMessage getCallVolunteerMessage(long chatId) {
        return new SendMessage(chatId, InfoStrings.BotMessageTextProvider.getCallVolunteerMessage());
    }

    public static EditMessageText getAboutMessageTextEdit(Message message) {
        return new EditMessageText(message.chat().id(), message.messageId(), getAboutShelterMenuMessage());
    }

    public static EditMessageReplyMarkup getAboutMessageMarkupEdit (Message message) {
        var result = new EditMessageReplyMarkup(message.chat().id(), message.messageId());
        var keyboard = assembleKeyboard(EnumSet.of(TgButton.ABOUT_GENERAL_BUTTON, TgButton.ABOUT_CONTACTS_BUTTON,
                        TgButton.ABOUT_PERMIT_BUTTON, TgButton.ABOUT_RULES_ON_TERRITORY));
        return result.replyMarkup(keyboard);
    }

    public static EditMessageText getAboutGeneralMessageTextEdit(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getGeneralInfo(preference));
    }

    public static EditMessageReplyMarkup getAboutGeneralMessageMarkupEdit (Message message) {
        var result = new EditMessageReplyMarkup(message.chat().id(), message.messageId());
        var keyboard = assembleKeyboard(EnumSet.of(TgButton.ABOUT_CONTACTS_BUTTON, TgButton.ABOUT_PERMIT_BUTTON,
                TgButton.ABOUT_RULES_ON_TERRITORY, TgButton.BACK_MAIN_BUTTON));
        return result.replyMarkup(keyboard);
    }

    public static EditMessageText getAboutContactsMessageTextEdit(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getContactsInfo(preference));
    }

    public static EditMessageReplyMarkup getAboutContactsMessageMarkupEdit (Message message) {
        var result = new EditMessageReplyMarkup(message.chat().id(), message.messageId());
        var keyboard = assembleKeyboard(EnumSet.of(TgButton.ABOUT_GENERAL_BUTTON, TgButton.ABOUT_PERMIT_BUTTON,
                TgButton.ABOUT_RULES_ON_TERRITORY, TgButton.BACK_MAIN_BUTTON));
        return result.replyMarkup(keyboard);
    }

    public static EditMessageText getAboutEntryPermitMessageTextEdit(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getEntryPermitInfo(preference));
    }

    public static EditMessageReplyMarkup getAboutEntryPermitMessageMarkupEdit (Message message) {
        var result = new EditMessageReplyMarkup(message.chat().id(), message.messageId());
        var keyboard = assembleKeyboard(EnumSet.of(TgButton.ABOUT_GENERAL_BUTTON, TgButton.ABOUT_CONTACTS_BUTTON,
                TgButton.ABOUT_RULES_ON_TERRITORY, TgButton.BACK_MAIN_BUTTON));
        return result.replyMarkup(keyboard);
    }

    public static EditMessageText getAboutRulesMessageTextEdit(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getRulesOnTerritoryInfo(preference));
    }

    public static EditMessageReplyMarkup getAboutRulesMessageMarkupEdit (Message message) {
        var result = new EditMessageReplyMarkup(message.chat().id(), message.messageId());
        var keyboard = assembleKeyboard(EnumSet.of(TgButton.ABOUT_GENERAL_BUTTON, TgButton.ABOUT_CONTACTS_BUTTON,
                TgButton.ABOUT_PERMIT_BUTTON, TgButton.BACK_MAIN_BUTTON));
        return result.replyMarkup(keyboard);
    }

    public static EditMessageText getBackToMainTextEdit(Message message) {
        return new EditMessageText(message.chat().id(), message.messageId(), getBackToMainMenuMessage());
    }

    public static EditMessageReplyMarkup getBackToMainMenuMarkupEdit (Message message, EnumSet<TgButton> buttons) {
        var result = new EditMessageReplyMarkup(message.chat().id(), message.messageId());
        var keyboard = assembleKeyboard(buttons);
        return result.replyMarkup(keyboard);
    }





    private static InlineKeyboardMarkup assembleKeyboard(EnumSet<TgButton> buttons) {
        InlineKeyboardMarkup result = new InlineKeyboardMarkup();
        for (var button : buttons) {
            result.addRow(button.getButton());
        }
        return result;
    }
}
