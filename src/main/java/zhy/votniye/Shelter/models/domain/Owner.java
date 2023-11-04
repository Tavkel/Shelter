package zhy.votniye.Shelter.models.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import zhy.votniye.Shelter.models.enums.Status;

import java.util.List;
import java.util.Objects;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "telegram_chat_id")
    private Long telegramChatId;
    private String telegramHandle;
    private Long phoneNumber;
    private String email;
    private String address;
    private String comment;
    private Status.OwnerStatus status;
    private Status.OwnerPreference preference;
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Cat> cats;
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Dog> dogs;
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<AdoptionProcessMonitor> reportMonitors;

    public Owner(long id, String firstName,
                 String lastName, String middleName,
                 Status.OwnerStatus status, List<Pet> pets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.status = status;
    }

    public Owner(long id){
        this.id = id;
    }

    public Owner() {
        status = Status.OwnerStatus.AWAITING_CONFIRMATION;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public List<AdoptionProcessMonitor> getReportMonitors() {
        return reportMonitors;
    }

    public void setReportMonitors(List<AdoptionProcessMonitor> reportMonitors) {
        this.reportMonitors = reportMonitors;
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
        Owner owner = (Owner) o;
        return Objects.equals(firstName, owner.firstName) && Objects.equals(lastName, owner.lastName)
                && Objects.equals(middleName, owner.middleName)
                && Objects.equals(telegramChatId, owner.telegramChatId)
                && Objects.equals(telegramHandle, owner.telegramHandle)
                && Objects.equals(phoneNumber, owner.phoneNumber) && Objects.equals(email, owner.email)
                && Objects.equals(address, owner.address) && Objects.equals(comment, owner.comment)
                && status == owner.status;
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
        return "Owner{" +
                "id=" + id +
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
                ", preference=" + preference +
                '}';
    }
}
