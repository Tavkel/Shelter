package zhy.votniye.Shelter.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Contact {
    @Id
    private long id;
    private long phone;
    @Column(name = "telegram_chat_id")
    private long telegramChatId;
    private String email;
    private String address;
    private String comment;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Owner owner;

    public Contact() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public long getTelegramChatId() {
        return telegramChatId;
    }

    public void setTelegramChatId(long telegramChatId) {
        this.telegramChatId = telegramChatId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact contact)) return false;
        return telegramChatId ==
                contact.telegramChatId && Objects.equals(phone, contact.phone)
                && Objects.equals(email, contact.email)
                && Objects.equals(address, contact.address)
                && Objects.equals(comment, contact.comment)
                && Objects.equals(owner, contact.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, telegramChatId, email, address, comment, owner);
    }
}

