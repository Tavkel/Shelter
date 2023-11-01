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
        return new EditMessageText(message.chat().id(), message.messageId(), getAboutShelterMenuMessage())
                .replyMarkup(getAboutShelterMenuKeyboard());
    }

    public static EditMessageText getAboutGeneralMessageTextEdit(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getGeneralInfo(preference))
                .replyMarkup(getAboutShelterMenuKeyboard());
    }

    public static EditMessageText getAboutContactsMessageTextEdit(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getContactsInfo(preference))
                .replyMarkup(getAboutShelterMenuKeyboard());
    }

    public static EditMessageText getAboutEntryPermitMessageTextEdit(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getEntryPermitInfo(preference))
                .replyMarkup(getAboutShelterMenuKeyboard());
    }

    public static EditMessageText getAboutRulesMessageTextEdit(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getRulesOnTerritoryInfo(preference))
                .replyMarkup(getAboutShelterMenuKeyboard());
    }

    public static EditMessageText getAboutAdoptionMenuTextEdit(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), getAboutAdoptionMenuMessage())
                .replyMarkup(getAboutAdoptionMenuKeyboard(preference));
    }

    public static EditMessageText getMeetingPetMessage(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getAboutMeetingPetMessage(preference))
                .replyMarkup(getAboutAdoptionMenuKeyboard(preference));
    }

    public static EditMessageText getAboutRequiredDocumentsMessage(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getAboutRequiredDocumentsMessage(preference))
                .replyMarkup(getAboutAdoptionMenuKeyboard(preference));
    }

    public static EditMessageText getAboutPetTransportationMessage(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getAboutPetTransportationMessage(preference))
                .replyMarkup(getAboutAdoptionMenuKeyboard(preference));
    }

    public static EditMessageText getAboutAccommodatingPetMessage(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getAboutAccommodatingPetMessage(preference))
                .replyMarkup(getAboutAdoptionMenuKeyboard(preference));
    }

    public static EditMessageText getAboutAccommodatingYoungPetMessage(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getAboutAccommodatingYoungPetMessage(preference))
                .replyMarkup(getAboutAdoptionMenuKeyboard(preference));
    }

    public static EditMessageText getAboutAccommodatingDisabledPetMessage(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getAboutAccommodatingDisabledPetMessage(preference))
                .replyMarkup(getAboutAdoptionMenuKeyboard(preference));
    }

    public static EditMessageText getAboutAdoptionRejectMessage(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.getAboutAdoptionRejectMessage(preference))
                .replyMarkup(getAboutAdoptionMenuKeyboard(preference));
    }

    public static EditMessageText getBackToMainTextEdit(Message message) {
        return new EditMessageText(message.chat().id(), message.messageId(), getBackToMainMenuMessage());
    }

    public static EditMessageReplyMarkup getBackToMainMenuMarkupEdit(Message message, EnumSet<TgButton> buttons) {
        var result = new EditMessageReplyMarkup(message.chat().id(), message.messageId());
        var keyboard = assembleKeyboard(buttons);
        return result.replyMarkup(keyboard);
    }

    public static EditMessageText getAboutCynologistAdviceMessage(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(),InfoStrings.DogShelterInfoProvider.getAboutCynologistAdviceMessage() )
                .replyMarkup(getAboutAdoptionMenuKeyboard(preference));
    }

    public static EditMessageText getAboutCynologistContactsMessage(Message message, Status.OwnerPreference preference) {
        return new EditMessageText(message.chat().id(), message.messageId(), InfoStrings.DogShelterInfoProvider.getAboutCynologistContactsMessage())
                .replyMarkup(getAboutAdoptionMenuKeyboard(preference));
    }

    private static InlineKeyboardMarkup getAboutAdoptionMenuKeyboard(Status.OwnerPreference preference) {
        var keyboard = new InlineKeyboardMarkup(
                TgButton.ABOUT_GETTING_FAMILIAR_WITH_A_PET_BUTTON.getButton(), TgButton.ABOUT_REQUIRED_DOCUMENTS_BUTTON.getButton())
                .addRow(TgButton.ABOUT_PET_TRANSPORTATION_BUTTON.getButton(), TgButton.ABOUT_LIVING_SPACE_FOR_YOUNG_PET_BUTTON.getButton())
                .addRow(TgButton.ABOUT_LIVING_SPACE_FOR_PET_BUTTON.getButton(), TgButton.ABOUT_LIVING_SPACE_FOR_DISABLED_PET_BUTTON.getButton());
        if (preference == Status.OwnerPreference.DOG) {
            keyboard.addRow(TgButton.ABOUT_CYNOLOGIST_CONTACTS_BUTTON.getButton(), TgButton.ABOUT_CYNOLOGIST_ADVICES_BUTTON.getButton());
        }


        keyboard.addRow(TgButton.ABOUT_WHY_ADOPTION_REQUEST_MIGHT_BE_REFUSED_BUTTON.getButton())
                .addRow(TgButton.BACK_MAIN_BUTTON.getButton());
        return keyboard;
    }

    private static InlineKeyboardMarkup getAboutShelterMenuKeyboard() {
        return new InlineKeyboardMarkup(TgButton.ABOUT_GENERAL_BUTTON.getButton())
                .addRow(TgButton.ABOUT_CONTACTS_BUTTON.getButton())
                .addRow(TgButton.ABOUT_PERMIT_BUTTON.getButton())
                .addRow(TgButton.ABOUT_RULES_ON_TERRITORY.getButton())
                .addRow(TgButton.ABOUT_ADOPTION_BUTTON.getButton())
                .addRow(TgButton.BACK_MAIN_BUTTON.getButton());
    }

    private static InlineKeyboardMarkup assembleKeyboard(EnumSet<TgButton> buttons) {
        InlineKeyboardMarkup result = new InlineKeyboardMarkup();
        for (var button : buttons) {
            result.addRow(button.getButton());
        }
        return result;
    }
}
