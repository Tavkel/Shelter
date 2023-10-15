package zhy.votniye.Shelter.models.domain;

import jakarta.persistence.*;
import zhy.votniye.Shelter.models.enums.Gender;
import zhy.votniye.Shelter.models.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Gender gender;
    private String breed;
    private Float weight;
    private LocalDateTime dateOfBirth;
    private byte[] photo;
    private Status.PetStatus status;

    @Column(name = "path_to_file")
    private String pathToFile;

    private String description;
    @Column(name = "special_needs")
    private String specialNeeds;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Pet() {
        status = Status.PetStatus.AVAILABLE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        Period age = Period.between(dateOfBirth.toLocalDate(), LocalDate.now());
        return age.getYears();
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(String specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Status.PetStatus getStatus() {
        return status;
    }

    public void setStatus(Status.PetStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(dateOfBirth, pet.dateOfBirth)
                && Objects.equals(name, pet.name)
                && Objects.equals(breed, pet.breed)
                && Objects.equals(weight, pet.weight)
                && Arrays.equals(photo, pet.photo)
                && Objects.equals(pathToFile, pet.pathToFile)
                && Objects.equals(description, pet.description)
                && Objects.equals(specialNeeds, pet.specialNeeds);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, breed, weight, dateOfBirth, pathToFile, description, specialNeeds);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

}
