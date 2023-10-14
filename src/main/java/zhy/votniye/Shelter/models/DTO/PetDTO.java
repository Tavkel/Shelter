package zhy.votniye.Shelter.models.DTO;

import java.util.Arrays;
import java.util.Objects;

public class PetDTO {

    private Long petId;
    private String name;
    private String breed;
    private Float weight;
    private int age;
    private byte[] photo;
    private String filePathPetPhoto;
    private String description;
    private String specialNeeds;


//    private status;


    public PetDTO(Long petId, String name, String breed, Float weight,
                  int age, byte[] photo, String filePathPetPhoto,
                  String description, String specialNeeds, OwnerDTO ownerDTO) {
        this.petId = petId;
        this.name = name;
        this.breed = breed;
        this.weight = weight;
        this.age = age;
        this.photo = photo;
        this.filePathPetPhoto = filePathPetPhoto;
        this.description = description;
        this.specialNeeds = specialNeeds;

    }

    public PetDTO(){

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetDTO petDTO = (PetDTO) o;
        return age == petDTO.age && Objects.equals(petId, petDTO.petId) && Objects.equals(name, petDTO.name)
                && Objects.equals(breed, petDTO.breed) && Objects.equals(weight, petDTO.weight)
                && Arrays.equals(photo, petDTO.photo) && Objects.equals(filePathPetPhoto, petDTO.filePathPetPhoto)
                && Objects.equals(description, petDTO.description) && Objects.equals(specialNeeds, petDTO.specialNeeds);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(petId, name, breed, weight, age, filePathPetPhoto, description, specialNeeds);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
