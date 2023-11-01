package zhy.votniye.Shelter.services.implementations.tg;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.EditMessageReplyMarkup;
import com.pengrad.telegrambot.request.EditMessageText;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.exceptions.GetOwnerPreferenceException;
import zhy.votniye.Shelter.models.domain.UnregisteredOwner;
import zhy.votniye.Shelter.models.enums.Status;
import zhy.votniye.Shelter.services.interfaces.OwnerService;
import zhy.votniye.Shelter.services.interfaces.UnregisteredOwnerService;
import zhy.votniye.Shelter.services.interfaces.tg.TgBotService;
import zhy.votniye.Shelter.services.interfaces.tg.TgCallbackService;
import zhy.votniye.Shelter.utils.tgUtility.TgMessageBuilder;

@Service
public class TgCallbackServiceImpl implements TgCallbackService {
    private final TelegramBot telegramBot;
    private final TgBotService botService;

    private final OwnerService ownerService;

    private final UnregisteredOwnerService unregisteredOwnerService;


    public TgCallbackServiceImpl(TelegramBot telegramBot, TgBotService botService, OwnerService ownerService, UnregisteredOwnerService unregisteredOwnerService) {
        this.telegramBot = telegramBot;
        this.botService = botService;
        this.ownerService = ownerService;
        this.unregisteredOwnerService = unregisteredOwnerService;
    }

    @Override
    public void callVolunteer(Message message) {
        var response = TgMessageBuilder.getCallVolunteerMessage(message.chat().id());
        telegramBot.execute(response);
        cleanUpButtons(message);
    }

    @Override
    public void chooseCat(Message message) {

        makeChoice(message, Status.OwnerPreference.CAT);
    }

    @Override
    public void chooseDog(Message message) {
        makeChoice(message, Status.OwnerPreference.DOG);
    }

    private void makeChoice(Message message, Status.OwnerPreference preference) {
        long chatId = message.chat().id();

        try {
            botService.getOwnerPreference(chatId);

            var owner = ownerService.getByChatId(chatId);

            if (owner.isPresent()) {
                owner.get().setPreference(preference);
                ownerService.update(owner.get());
            } else {
                var unregOwner = unregisteredOwnerService.read(chatId);
                unregOwner.get().setPreference(preference);
                unregisteredOwnerService.update(unregOwner.get());
            }
        } catch (GetOwnerPreferenceException e) {
            var unregOwner = new UnregisteredOwner(chatId, preference);
            unregisteredOwnerService.create(unregOwner);
        } finally {
            var buttons = botService.getAppropriateButtons(chatId);
            var res = TgMessageBuilder.getStartMessage(chatId, buttons);

            cleanUpButtons(message);

            telegramBot.execute(res);
        }
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
        EditMessageText editText = TgMessageBuilder.getAboutAdoptionMenuTextEdit(message, preference);
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

    @Override
    public void aboutCynologistAdvice(Message message) {
        var preference = botService.getOwnerPreference(message.chat().id());
        EditMessageText editMessageText = TgMessageBuilder.getAboutCynologistAdviceMessage(message, preference);
        telegramBot.execute(editMessageText);
    }

    @Override
    public void aboutCynologistContacts(Message message) {
        var preference = botService.getOwnerPreference(message.chat().id());
        EditMessageText editMessageText = TgMessageBuilder.getAboutCynologistContactsMessage(message, preference);
        telegramBot.execute(editMessageText);
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
