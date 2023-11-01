package zhy.votniye.Shelter.utils.mappers;

import org.springframework.stereotype.Component;
import zhy.votniye.Shelter.models.DTO.CatDTO;
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.domain.Cat;
import zhy.votniye.Shelter.models.domain.Pet;

@Component
public class CatMapper implements PetMapper<Cat, CatDTO> {

    public Cat toPet(CatDTO petDTO) {
        if (petDTO == null) {
            throw new NullPointerException("Tried to map null to Pet");
        }

        Cat cat = new Cat();

        cat.setId(petDTO.getPetId());
        cat.setName(petDTO.getName());
        cat.setBreed(petDTO.getBreed());
        cat.setSex(petDTO.getSex());
        cat.setWeight(petDTO.getWeight());
        cat.setDateOfBirth(petDTO.getDateOfBirth());
        cat.setDescription(petDTO.getDescription());
        cat.setSpecialNeeds(petDTO.getSpecialNeeds());
        cat.setStatus(petDTO.getStatus());

        return cat;
    }

    public CatDTO fromPet(Cat pet) {
        if (pet == null) {
            throw new NullPointerException("Tried to map null to PetDTO");
        }

        CatDTO catDTO = new CatDTO();

        catDTO.setPetId(pet.getId());
        catDTO.setName(pet.getName());
        catDTO.setBreed(pet.getBreed());
        catDTO.setSex(pet.getSex());
        catDTO.setWeight(pet.getWeight());
        catDTO.setAge(pet.getAge());
        catDTO.setDateOfBirth(pet.getDateOfBirth());
        catDTO.setPhoto(pet.getPhoto());
        catDTO.setMediaType(pet.getMediaType());
        catDTO.setDescription(pet.getDescription());
        catDTO.setSpecialNeeds(pet.getSpecialNeeds());
        catDTO.setStatus(pet.getStatus());

        return catDTO;
    }
}
