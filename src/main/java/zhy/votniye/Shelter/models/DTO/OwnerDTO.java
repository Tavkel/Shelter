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
    private Long telegramChatId;
    private String telegramHandle;
    private Long phoneNumber;
    private String email;
    private String address;
    private String comment;
    private Status.OwnerStatus status;
    private Status.OwnerPreference preference;

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

    public Long getTelegramChatId() {
        return telegramChatId;
    }

    public void setTelegramChatId(Long telegramChatId) {
        this.telegramChatId = telegramChatId;
    }

    public String getTelegramHandle() {
        return telegramHandle;
    }

    public void setTelegramHandle(String telegramHandle) {
        this.telegramHandle = telegramHandle;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        return Objects.equals(firstName, ownerDTO.firstName) && Objects.equals(lastName, ownerDTO.lastName)
                && Objects.equals(middleName, ownerDTO.middleName)
                && Objects.equals(telegramChatId, ownerDTO.telegramChatId)
                && Objects.equals(telegramHandle, ownerDTO.telegramHandle)
                && Objects.equals(phoneNumber, ownerDTO.phoneNumber) && Objects.equals(email, ownerDTO.email)
                && Objects.equals(address, ownerDTO.address) && Objects.equals(comment, ownerDTO.comment)
                && status == ownerDTO.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName,
                middleName, telegramChatId,
                phoneNumber, telegramHandle,
                email, address,
                comment, status);
    }

    @Override
    public String toString() {
        return "OwnerDTO{" +
                "ownerId=" + ownerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", telegramChatId=" + telegramChatId +
                ", telegramHandle='" + telegramHandle + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                '}';
    }
}
