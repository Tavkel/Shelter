package zhy.votniye.Shelter.sessions.tg;

import zhy.votniye.Shelter.services.interfaces.ContactService;
import zhy.votniye.Shelter.services.interfaces.TgBotService;

import java.time.LocalDateTime;

public class TgSession {
    private final long chatId;

    private LocalDateTime lastUpdate;

    private int step = 1;

    private String data;

    private final TgSessionTypes type;

    private TgSessionModelAssembler observer;

    private final TgBotService botService;
    private ContactService contactService;

    public TgSession(long chatId, TgSessionTypes type, TgBotService botService) {
        this.chatId = chatId;
        this.botService = botService;
        this.lastUpdate = LocalDateTime.now();
        this.type = type;
        this.setObserver(new TgSessionModelAssembler());
    }

    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    public long getChatId() {
        return chatId;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets incoming data string and calls observer to process it.
     * Calls {@link TgBotService} to form response based on result of {@link TgSessionModelAssembler#updateOwner(String, int)}
     *
     * @param data
     */
    //todo rework to work with any model
    public void setData(String data) {
        this.data = data;
        lastUpdate = LocalDateTime.now();
        byte updResult = observer.updateOwner(data, step);

        if (updResult == 1) { //success
            step++;
            botService.leaveContactStep(chatId, step);
        } else if (updResult == 0) { //success last step
            step++;
            var contact = observer.getContact();
            contact.setTelegramChatId(chatId);
            //move to TgService?
            try {
                contactService.create(contact);
            } catch (Exception e) {
                botService.sendErrorReport(chatId, e.getMessage());
            }
            botService.leaveContactStep(chatId, step);
        } else { //failure
            botService.dataIngestSessionFailure(chatId, step);
        }
    }

    public void setObserver(TgSessionModelAssembler observer) {
        this.observer = observer;
    }

    public void removeObserver() {
        this.observer = null;
    }

    public void destroy(){
        this.observer.destroy();
        removeObserver();
    }
}
