package zhy.votniye.Shelter.utils.mappers;

import org.springframework.stereotype.Component;
import zhy.votniye.Shelter.models.DTO.DogDTO;
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.domain.Dog;

@Component
public class DogMapper implements PetMapper<Dog, DogDTO> {

    public Dog toPet(DogDTO petDTO) {
        if (petDTO == null) {
            throw new NullPointerException("Tried to map null to Pet");
        }

        Dog Dog = new Dog();

        Dog.setId(petDTO.getPetId());
        Dog.setName(petDTO.getName());
        Dog.setBreed(petDTO.getBreed());
        Dog.setSex(petDTO.getSex());
        Dog.setWeight(petDTO.getWeight());
        Dog.setDateOfBirth(petDTO.getDateOfBirth());
        Dog.setDescription(petDTO.getDescription());
        Dog.setSpecialNeeds(petDTO.getSpecialNeeds());
        Dog.setStatus(petDTO.getStatus());

        return Dog;
    }

    public DogDTO fromPet(Dog pet) {
        if (pet == null) {
            throw new NullPointerException("Tried to map null to PetDTO");
        }

        DogDTO DogDTO = new DogDTO();

        DogDTO.setPetId(pet.getId());
        DogDTO.setName(pet.getName());
        DogDTO.setBreed(pet.getBreed());
        DogDTO.setSex(pet.getSex());
        DogDTO.setWeight(pet.getWeight());
        DogDTO.setAge(pet.getAge());
        DogDTO.setDateOfBirth(pet.getDateOfBirth());
        DogDTO.setPhoto(pet.getPhoto());
        DogDTO.setMediaType(pet.getMediaType());
        DogDTO.setDescription(pet.getDescription());
        DogDTO.setSpecialNeeds(pet.getSpecialNeeds());
        DogDTO.setStatus(pet.getStatus());

        return DogDTO;
    }
}
