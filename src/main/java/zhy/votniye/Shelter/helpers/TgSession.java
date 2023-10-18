package zhy.votniye.Shelter.helpers;

import org.springframework.beans.factory.annotation.Configurable;
import zhy.votniye.Shelter.services.interfaces.ContactService;
import zhy.votniye.Shelter.services.interfaces.TgBotService;

import java.time.LocalDateTime;

@Configurable
public class TgSession implements Runnable {
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
        this.run();
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

    public void setData(String data) {
        this.data = data;
        lastUpdate = LocalDateTime.now();
//        if (type == TgSessionTypes.LEAVE_CONTACT && observer.updateOwner(data, step++) == 1) {
//            contactService.create(observer.getContact());
//            removeObserver();
//            return;
//        } else if (type == TgSessionTypes.SUBMIT_REPORT) {
//            removeObserver();
//            return;
//        }
        byte updResult = observer.updateOwner(data, step);
        if (updResult == 1) { //success
            step++;
            botService.leaveContactStep(chatId, step);
        } else if (updResult == 0) { //success last step
            step++;
            var contact = observer.getContact();
            contact.setTelegramChatId(chatId);
            try {
                contactService.create(contact);
            } catch (Exception e) {

            }
            botService.leaveContactStep(chatId, step);
            destroy();
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

    @Override
    public void run() {
        var observer = new TgSessionModelAssembler();
        this.setObserver(observer);
    }
}
