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
    private Status.OwnerStatus status;
    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Pet> pets;

    private Status.OwnerPreference preference;


    public Owner(long id, String firstName, String lastName, String middleName, Status.OwnerStatus status, List<Pet> pets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.status = status;
        this.pets = pets;
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
        Owner owner = (Owner) o;
        return Objects.equals(firstName, owner.firstName) && Objects.equals(lastName, owner.lastName) && Objects.equals(middleName, owner.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName);
    }
}
