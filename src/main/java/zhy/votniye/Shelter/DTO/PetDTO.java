package zhy.votniye.Shelter.DTO;

import jakarta.persistence.ManyToOne;

public class PetDTO {

    private Long petIdDTO;
    private String name;
    private String breed;
    private Float weight;
    private int age;
    private byte[] photo;
    private String filePathPetPhoto;
    private String description;
    private String specialNeeds;
    private OwnerDTO ownerDTO;

//    private status;


    public PetDTO(Long petIdDTO, String name, String breed, Float weight,
                  int age, byte[] photo, String filePathPetPhoto,
                  String description, String specialNeeds, OwnerDTO ownerDTO) {
        this.petIdDTO = petIdDTO;
        this.name = name;
        this.breed = breed;
        this.weight = weight;
        this.age = age;
        this.photo = photo;
        this.filePathPetPhoto = filePathPetPhoto;
        this.description = description;
        this.specialNeeds = specialNeeds;
        this.ownerDTO = ownerDTO;
    }

    public Long getPetIdDTO() {
        return petIdDTO;
    }

    public void setPetIdDTO(Long petIdDTO) {
        this.petIdDTO = petIdDTO;
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

    public OwnerDTO getOwnerDTO() {
        return ownerDTO;
    }

    public void setOwnerDTO(OwnerDTO ownerDTO) {
        this.ownerDTO = ownerDTO;
    }
}
