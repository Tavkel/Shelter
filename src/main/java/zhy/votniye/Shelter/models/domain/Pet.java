package zhy.votniye.Shelter.models.domain;

import jakarta.persistence.*;
import zhy.votniye.Shelter.models.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.Objects;

@MappedSuperclass
public abstract class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean isMale;
    private String breed;
    private Float weight;
    private LocalDateTime dateOfBirth;
    private Long fileSize;
    private String mediaType;
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

    public Pet(long id) {
        this.id = id;
    }

    public Pet(Long id, String name, Boolean isMale,
               String breed, Float weight,
               LocalDateTime dateOfBirth,
               byte[] photo, Status.PetStatus status,
               String pathToFile, String description,
               String specialNeeds, Owner owner) {
        this.id = id;
        this.name = name;
        this.isMale = isMale;
        this.breed = breed;
        this.weight = weight;
        this.dateOfBirth = dateOfBirth;
        this.photo = photo;
        this.status = status;
        this.pathToFile = pathToFile;
        this.description = description;
        this.specialNeeds = specialNeeds;
        this.owner = owner;
    }

    public Long getFileSize(){
        return fileSize;
    }
    public void setFileSize(Long fileSize){
        this.fileSize = fileSize;
    }
    public String getMediaType(){
        return mediaType;
    }
    public void setMediaType(String mediaType){
        this.mediaType = mediaType;
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

    public Boolean getSex() {
        return isMale;
    }

    public void setSex(Boolean male) {
        isMale = male;
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
                && Objects.equals(isMale, pet.isMale)
                && Objects.equals(name, pet.name)
                && Objects.equals(breed, pet.breed)
                && Objects.equals(weight, pet.weight)
                && Objects.equals(description, pet.description)
                && Objects.equals(specialNeeds, pet.specialNeeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isMale, breed, weight, dateOfBirth,
                     pathToFile, description, specialNeeds);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isMale=" + isMale +
                ", breed='" + breed + '\'' +
                ", weight=" + weight +
                ", dateOfBirth=" + dateOfBirth +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", status=" + status +
                ", pathToFile='" + pathToFile + '\'' +
                ", description='" + description + '\'' +
                ", specialNeeds='" + specialNeeds + '\'' +
                ", owner=" + owner +
                '}';
    }
}
