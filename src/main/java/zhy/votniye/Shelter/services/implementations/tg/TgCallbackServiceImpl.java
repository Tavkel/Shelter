package zhy.votniye.Shelter.services.implementations.tg;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageReplyMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import zhy.votniye.Shelter.models.enums.Status;
import zhy.votniye.Shelter.services.interfaces.tg.TgCallbackService;
import zhy.votniye.Shelter.utils.tgUtility.TgButton;
import zhy.votniye.Shelter.utils.tgUtility.TgMessageBuilder;

import java.util.EnumSet;

public class TgCallbackServiceImpl implements TgCallbackService {
    private final TelegramBot telegramBot;

    public TgCallbackServiceImpl(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void callVolunteer(Message message) {
        var response = TgMessageBuilder.getCallVolunteerMessage(message.chat().id());
        telegramBot.execute(response);
        cleanUpButtons(message);
    }

    @Override
    public void about(Message message) {
        EditMessageText editText = TgMessageBuilder.getAboutMessageTextEdit(message);
        EditMessageReplyMarkup editButtons = TgMessageBuilder.getAboutMessageMarkupEdit(message);
        executeAll(editText, editButtons);
    }

    @Override
    public void aboutGeneral(Message message) {
        //owner preference logic
        //todo remove placeholder
        EditMessageText editText = TgMessageBuilder.getAboutGeneralMessageTextEdit(message, Status.OwnerPreference.CAT);
        EditMessageReplyMarkup editButtons = TgMessageBuilder.getAboutGeneralMessageMarkupEdit(message);
        executeAll(editText, editButtons);
    }

    @Override
    public void aboutContacts(Message message) {
        //owner preference logic
        //todo remove placeholder
        EditMessageText editText = TgMessageBuilder.getAboutContactsMessageTextEdit(message, Status.OwnerPreference.CAT);
        EditMessageReplyMarkup editButtons = TgMessageBuilder.getAboutContactsMessageMarkupEdit(message);
        executeAll(editText, editButtons);
    }

    @Override
    public void aboutEntryPermit(Message message) {
        //owner preference logic
        //todo remove placeholder
        EditMessageText editText = TgMessageBuilder.getAboutEntryPermitMessageTextEdit(message, Status.OwnerPreference.CAT);
        EditMessageReplyMarkup editButtons = TgMessageBuilder.getAboutEntryPermitMessageMarkupEdit(message);
        executeAll(editText, editButtons);
    }

    @Override
    public void aboutRulesOnTerritory(Message message) {
        //owner preference logic
        //todo remove placeholder
        EditMessageText editText = TgMessageBuilder.getAboutRulesMessageTextEdit(message, Status.OwnerPreference.CAT);
        EditMessageReplyMarkup editButtons = TgMessageBuilder.getAboutRulesMessageMarkupEdit(message);
        executeAll(editText, editButtons);
    }

    @Override
    public void aboutAdoption(Message message) {
        //owner preference logic
        //todo remove placeholder
        EditMessageText editText = TgMessageBuilder.
    }

    @Override
    public void backToMain(Message message) {
        EnumSet<TgButton> buttons = EnumSet.of(TgButton.ABOUT_SHELTER_BUTTON, TgButton.CALL_VOLUNTEER_BUTTON);
        //todo
        // -button set logic
        // -if user has pet on prob period - add submit report
        // -if user not registered - add leave contact
        EditMessageText editText = TgMessageBuilder.getBackToMainTextEdit(message);
        EditMessageReplyMarkup editButtons = TgMessageBuilder.getBackToMainMenuMarkupEdit(message, buttons);
        executeAll(editText, editButtons);
    }

    @Override
    public void leaveContact(Message message) {
//todo implement leave contacts
    }

    @Override
    public void submitReport(Message message) {
//todo implement submit report?
    }

    @Override
    public void aboutGettingFamiliarWithAPet(Message message) {
        //owner preference logic
        //todo remove placeholder
        EditMessageText editText = TgMessageBuilder.getAboutRulesMessageTextEdit(message, Status.OwnerPreference.CAT);
        EditMessageReplyMarkup editButtons = TgMessageBuilder.getAboutRulesMessageMarkupEdit(message);
        executeAll(editText, editButtons);
    }

    @Override
    public void aboutRequiredDocuments(Message message) {

    }

    @Override
    public void aboutPetTransportation(Message message) {

    }

    @Override
    public void aboutLivingSpaceForYoungPet(Message message) {

    }

    @Override
    public void aboutLivingSpaceForPet(Message message) {

    }

    @Override
    public void aboutLivingSpaceForDisabledPet(Message message) {

    }

    @Override
    public void aboutWhyAdoptionRequestMightBeRefused(Message message) {

    }

    private void executeAll(BaseRequest... requests) {
        for (var req : requests) {
            telegramBot.execute(req);
        }
    }

    private void cleanUpButtons(Message message) {
        EditMessageReplyMarkup edit = new EditMessageReplyMarkup(
                message.chat().id(), message.messageId()).replyMarkup(new InlineKeyboardMarkup());
        telegramBot.execute(edit);
    }
}
