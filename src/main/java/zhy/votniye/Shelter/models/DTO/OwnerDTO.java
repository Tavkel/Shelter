package zhy.votniye.Shelter.models.DTO;

import java.util.Objects;

public class OwnerDTO {


    private Long ownerId;
    private String firstName;
    private String lastName;
    private String middleName;
    private ContactDTO contactDTO;

    public OwnerDTO(Long ownerId, String firstName, String lastName, String middleName, ContactDTO contactDTO) {
        this.ownerId = ownerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.contactDTO = contactDTO;
    }

    public OwnerDTO(){

    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerDTO ownerDTO = (OwnerDTO) o;
        return Objects.equals(ownerId, ownerDTO.ownerId) && Objects.equals(firstName, ownerDTO.firstName)
                && Objects.equals(lastName, ownerDTO.lastName) && Objects.equals(middleName, ownerDTO.middleName)
                && Objects.equals(contactDTO, ownerDTO.contactDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, firstName, lastName, middleName, contactDTO);
    }
}
