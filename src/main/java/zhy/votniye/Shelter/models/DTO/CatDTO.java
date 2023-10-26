package zhy.votniye.Shelter.models.DTO;

import java.time.LocalDateTime;

public class CatDTO extends PetDTO {
    public CatDTO(Long petId, String name, Boolean isMale, String breed, Float weight, LocalDateTime dateOfBirth, byte[] photo, String filePathPetPhoto, String description, String specialNeeds, OwnerDTO ownerDTO) {
        super(petId, name, isMale, breed, weight, dateOfBirth, photo, filePathPetPhoto, description, specialNeeds, ownerDTO);
    }

    public CatDTO() {
    }
}
