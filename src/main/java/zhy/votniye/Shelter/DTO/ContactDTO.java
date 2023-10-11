package zhy.votniye.Shelter.DTO;

public class ContactDTO {

    private Long contactIdDTO;
    private int phone;

//    private whatsapp;

//    private telegram;

//    private telegramChatId;

    private String email;
    private String address;
    private String comment;

    public ContactDTO(Long contactIdDTO, int phone, String email, String address, String comment) {
        this.contactIdDTO = contactIdDTO;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.comment = comment;
    }

    public Long getContactIdDTO() {
        return contactIdDTO;
    }

    public void setContactIdDTO(Long contactIdDTO) {
        this.contactIdDTO = contactIdDTO;
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
}
