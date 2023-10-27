package zhy.votniye.Shelter.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
@Deprecated
@Schema(title = "Contact")
public class ContactDTO {

    private Long contactId;
    private Long phone;
    private String email;
    private String address;
    private String comment;

    public ContactDTO(Long contactId, long phone, String email, String address, String comment) {
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

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
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
        return Objects.equals(phone, that.phone)
                && Objects.equals(email, that.email) && Objects.equals(address, that.address)
                && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email, address, comment);
    }
}
