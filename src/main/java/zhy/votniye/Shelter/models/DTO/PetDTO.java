package zhy.votniye.Shelter.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import zhy.votniye.Shelter.models.enums.Status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Schema(title = "Pet")
public abstract class PetDTO {
    private Long petId;
    private String name;
    private Boolean isMale;
    private String breed;
    private Float weight;
    private int age;
    private LocalDateTime dateOfBirth;
    private byte[] photo;
    private String filePathPetPhoto;
    private String description;
    private String specialNeeds;
    private Status.PetStatus status;

    public PetDTO(Long petId, String name, Boolean isMale, String breed, Float weight,
                  LocalDateTime dateOfBirth, byte[] photo, String filePathPetPhoto,
                  String description, String specialNeeds, OwnerDTO ownerDTO) {
        this.petId = petId;
        this.name = name;
        this.isMale = isMale;
        this.breed = breed;
        this.weight = weight;
        this.dateOfBirth = dateOfBirth;
        this.photo = photo;
        this.filePathPetPhoto = filePathPetPhoto;
        this.description = description;
        this.specialNeeds = specialNeeds;

    }

    public PetDTO() {
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
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

    @JsonProperty("Is male")
    public boolean getSex() {
        return isMale;
    }

    public void setSex(Boolean male) {
        isMale = male;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getFilePathPetPhoto() {
        return filePathPetPhoto;
    }

    public void setFilePathPetPhoto(String filePathPetPhoto) {
        this.filePathPetPhoto = filePathPetPhoto;
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
        PetDTO petDTO = (PetDTO) o;
        return
                 Objects.equals(name, petDTO.name)
                && Objects.equals(isMale, petDTO.isMale)
                && Objects.equals(breed, petDTO.breed)
                && Objects.equals(weight, petDTO.weight)
                && Objects.equals(dateOfBirth, petDTO.dateOfBirth)
                && Objects.equals(description, petDTO.description)
                && Objects.equals(specialNeeds, petDTO.specialNeeds)
                && status == petDTO.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isMale, breed, weight, dateOfBirth, description, specialNeeds, status);
    }

    @Override
    public String toString() {
        return "PetDTO{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", isMale=" + isMale +
                ", breed='" + breed + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                ", photo=" + Arrays.toString(photo) +
                ", filePathPetPhoto='" + filePathPetPhoto + '\'' +
                ", description='" + description + '\'' +
                ", specialNeeds='" + specialNeeds + '\'' +
                ", status=" + status +
                '}';
    }
}
