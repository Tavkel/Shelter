package zhy.votniye.Shelter.models.DTO;

import java.util.Objects;

public class ContactDTO {

    private Long contactId;
    private int phone;

//    private whatsapp;

//    private telegram;

//    private telegramChatId;

    private String email;
    private String address;
    private String comment;

    public ContactDTO(Long contactId, int phone, String email, String address, String comment) {
        this.contactId = contactId;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.comment = comment;
    }

    public ContactDTO(){

    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDTO that = (ContactDTO) o;
        return phone == that.phone && Objects.equals(contactId, that.contactId)
                && Objects.equals(email, that.email) && Objects.equals(address, that.address)
                && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, phone, email, address, comment);
    }
}
