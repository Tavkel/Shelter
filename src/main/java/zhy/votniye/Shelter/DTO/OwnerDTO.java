package zhy.votniye.Shelter.DTO;

import jakarta.persistence.OneToOne;

public class OwnerDTO {


    private Long ownerIdDTO;
    private String firstName;
    private String lastName;
    private String middleName;
    private ContactDTO contactDTO;

    public OwnerDTO(Long ownerIdDTO, String firstName, String lastName, String middleName, ContactDTO contactDTO) {
        this.ownerIdDTO = ownerIdDTO;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.contactDTO = contactDTO;
    }

    public Long getOwnerIdDTO() {
        return ownerIdDTO;
    }

    public void setOwnerIdDTO(Long ownerIdDTO) {
        this.ownerIdDTO = ownerIdDTO;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public ContactDTO getContactDTO() {
        return contactDTO;
    }

    public void setContactDTO(ContactDTO contactDTO) {
        this.contactDTO = contactDTO;
    }
}
