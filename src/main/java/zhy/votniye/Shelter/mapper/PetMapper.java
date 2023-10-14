package zhy.votniye.Shelter.mapper;

import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.Pet;

public class PetMapper {

    public Pet toPet(PetDTO petDTO) {
        if (petDTO == null) {
            return null;
        }

        Pet pet = new Pet();


        pet.setId(petDTO.getPetId());
        pet.setName(petDTO.getName());
        pet.setBreed(petDTO.getBreed());
        pet.setWeight(petDTO.getWeight());
        pet.setAge(petDTO.getAge());
        pet.setPathToFile(petDTO.getFilePathPetPhoto());
        pet.setDescription(petDTO.getDescription());
        pet.setSpecialNeeds(petDTO.getSpecialNeeds());

        return pet;
    }

    public PetDTO fromPet(Pet pet) {
        if (pet == null) {
            return null;
        }

        PetDTO petDTO = new PetDTO();

        petDTO.setPetId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setBreed(pet.getBreed());
        petDTO.setWeight(pet.getWeight());
        petDTO.setAge(pet.getAge());
        petDTO.setFilePathPetPhoto(pet.getPathToFile());
        petDTO.setDescription(pet.getDescription());
        petDTO.setSpecialNeeds(pet.getSpecialNeeds());


        return petDTO;
    }
}
