package zhy.votniye.Shelter.services.implementations.tg;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageReplyMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.services.interfaces.tg.TgBotService;
import zhy.votniye.Shelter.services.interfaces.tg.TgCallbackService;
import zhy.votniye.Shelter.utils.tgUtility.TgMessageBuilder;

@Service
public class TgCallbackServiceImpl implements TgCallbackService {
    private final TelegramBot telegramBot;
    private final TgBotService botService;

    public TgCallbackServiceImpl(TelegramBot telegramBot, TgBotService botService) {
        this.telegramBot = telegramBot;
        this.botService = botService;
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
        telegramBot.execute(editText);
    }

    @Override
    public void aboutGeneral(Message message) {
        var preference = botService.getOwnerPreference(message.chat().id());
        EditMessageText editText = TgMessageBuilder.getAboutGeneralMessageTextEdit(message, preference);
        telegramBot.execute(editText);
    }

    @Override
    public void aboutContacts(Message message) {
        var preference = botService.getOwnerPreference(message.chat().id());
        EditMessageText editText = TgMessageBuilder.getAboutContactsMessageTextEdit(message, preference);
        telegramBot.execute(editText);
    }

    @Override
    public void aboutEntryPermit(Message message) {
        var preference = botService.getOwnerPreference(message.chat().id());
        EditMessageText editText = TgMessageBuilder.getAboutEntryPermitMessageTextEdit(message, preference);
        telegramBot.execute(editText);
    }

    @Override
    public void aboutRulesOnTerritory(Message message) {
        var preference = botService.getOwnerPreference(message.chat().id());
        EditMessageText editText = TgMessageBuilder.getAboutRulesMessageTextEdit(message, preference);
        telegramBot.execute(editText);
    }

    @Override
    public void aboutAdoption(Message message) {

        var preference = botService.getOwnerPreference(message.chat().id());
        EditMessageText editText = TgMessageBuilder.getAboutAdoptionMenuTextEdit(message);
        telegramBot.execute(editText);
    }

    @Override
    public void backToMain(Message message) {
        var buttons = botService.getAppropriateButtons(message.chat().id());
        EditMessageText editText = TgMessageBuilder.getBackToMainTextEdit(message);
        EditMessageReplyMarkup editButtons = TgMessageBuilder.getBackToMainMenuMarkupEdit(message, buttons);
        executeAll(editText, editButtons);
    }

    @Override
    public void leaveContact(Message message) {
        botService.leaveContact(message);
    }

    @Override
    public void submitReport(Message message) {
//todo implement submit report?
    }

    @Override
    public void aboutGettingFamiliarWithAPet(Message message) {
        var preference = botService.getOwnerPreference(message.chat().id());
        EditMessageText editText = TgMessageBuilder.getMeetingPetMessage(message, preference);
        telegramBot.execute(editText);
    }

    @Override
    public void aboutRequiredDocuments(Message message) {
        var preference = botService.getOwnerPreference(message.chat().id());
        EditMessageText editText = TgMessageBuilder.getAboutRequiredDocumentsMessage(message, preference);
        telegramBot.execute(editText);
    }

    @Override
    public void aboutPetTransportation(Message message) {
        var preference = botService.getOwnerPreference(message.chat().id());
        EditMessageText editText = TgMessageBuilder.getAboutPetTransportationMessage(message, preference);
        telegramBot.execute(editText);
    }

    @Override
    public void aboutLivingSpaceForYoungPet(Message message) {
        var preference = botService.getOwnerPreference(message.chat().id());
        EditMessageText editText = TgMessageBuilder.getAboutAccommodatingYoungPetMessage(message, preference);
        telegramBot.execute(editText);
    }

    @Override
    public void aboutLivingSpaceForPet(Message message) {
        var preference = botService.getOwnerPreference(message.chat().id());
        EditMessageText editText = TgMessageBuilder.getAboutAccommodatingPetMessage(message, preference);
        telegramBot.execute(editText);
    }

    @Override
    public void aboutLivingSpaceForDisabledPet(Message message) {
        var preference = botService.getOwnerPreference(message.chat().id());
        EditMessageText editText = TgMessageBuilder.getAboutAccommodatingDisabledPetMessage(message, preference);
        telegramBot.execute(editText);
    }

    @Override
    public void aboutWhyAdoptionRequestMightBeRefused(Message message) {
        var preference = botService.getOwnerPreference(message.chat().id());
        EditMessageText editText = TgMessageBuilder.getAboutAdoptionRejectMessage(message, preference);
        telegramBot.execute(editText);
    }

    private void executeAll(BaseRequest... requests) {
        for (var req : requests) {
            telegramBot.execute(req);
        }
    }

    private void cleanUpButtons(Message message) {
        EditMessageReplyMarkup edit = new EditMessageReplyMarkup(message.chat().id(), message.messageId()).replyMarkup(new InlineKeyboardMarkup());
        telegramBot.execute(edit);
    }
}
