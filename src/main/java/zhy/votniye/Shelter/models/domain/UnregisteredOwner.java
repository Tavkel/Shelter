package zhy.votniye.Shelter.models.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import zhy.votniye.Shelter.models.enums.Status;

@Entity
public class UnregisteredOwner {
    @Id
    private long chatId;
    private Status.OwnerPreference preference;

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public Status.OwnerPreference getPreference() {
        return preference;
    }

    public void setPreference(Status.OwnerPreference preference) {
        this.preference = preference;
    }
}
