package zhy.votniye.Shelter.utils.mappers;

import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.domain.Cat;
import zhy.votniye.Shelter.models.domain.Pet;

public class PetMapper {

    public static toPet(PetDTO petDTO) {
        if (petDTO == null) {
            throw new NullPointerException("Tried to map null to Pet");
        }

        Pet pet = new Cat();

        pet.setId(petDTO.getPetId());
        pet.setName(petDTO.getName());
        pet.setBreed(petDTO.getBreed());
        pet.setSex(petDTO.getSex());
        pet.setWeight(petDTO.getWeight());
        pet.setDateOfBirth(petDTO.getDateOfBirth());
        pet.setPathToFile(petDTO.getFilePathPetPhoto());
        pet.setDescription(petDTO.getDescription());
        pet.setSpecialNeeds(petDTO.getSpecialNeeds());
        pet.setStatus(petDTO.getStatus());

        return pet;
    }

    public static PetDTO fromPet(Pet pet) {
        if (pet == null) {
            throw new NullPointerException("Tried to map null to PetDTO");
        }

        PetDTO petDTO = new PetDTO();

        petDTO.setPetId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setBreed(pet.getBreed());
        petDTO.setSex(pet.getSex());
        petDTO.setWeight(pet.getWeight());
        petDTO.setAge(pet.getAge());
        petDTO.setDateOfBirth(pet.getDateOfBirth());
        petDTO.setFilePathPetPhoto(pet.getPathToFile());
        petDTO.setDescription(pet.getDescription());
        petDTO.setSpecialNeeds(pet.getSpecialNeeds());
        petDTO.setStatus(pet.getStatus());

        return petDTO;
    }
}
