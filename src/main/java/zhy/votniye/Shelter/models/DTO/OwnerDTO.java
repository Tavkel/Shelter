package zhy.votniye.Shelter.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import zhy.votniye.Shelter.models.enums.Status;

import java.util.Objects;
@Schema(title = "Owner")
public class OwnerDTO {
    private Long ownerId;
    private String firstName;
    private String lastName;
    private String middleName;
    private ContactDTO contactDTO;
    private Status.OwnerStatus status;

    private Status.OwnerPreference preference;

    public OwnerDTO(Long ownerId, String firstName, String lastName, String middleName) {
        this.ownerId = ownerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;

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

    public Status.OwnerStatus getStatus() {
        return status;
    }

    public void setStatus(Status.OwnerStatus status) {
        this.status = status;
    }

    public Status.OwnerPreference getPreference(){
        return preference;
    }
    public void setPreference(Status.OwnerPreference preference){
        this.preference = preference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerDTO ownerDTO = (OwnerDTO) o;
        return  Objects.equals(firstName, ownerDTO.firstName)
                && Objects.equals(lastName, ownerDTO.lastName)
                && Objects.equals(middleName, ownerDTO.middleName);

    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName);
    }

    @Override
    public String toString() {
        return "OwnerDTO{" +
                "ownerId=" + ownerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }
}
