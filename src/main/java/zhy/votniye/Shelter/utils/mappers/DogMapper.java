package zhy.votniye.Shelter.utils.mappers;

import org.springframework.stereotype.Component;
import zhy.votniye.Shelter.models.DTO.DogDTO;
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.domain.Dog;
import zhy.votniye.Shelter.models.domain.Owner;

@Component
public class DogMapper implements PetMapper<Dog, DogDTO> {

    public Dog toPet(DogDTO dogDTO) {
        if (dogDTO == null) {
            throw new NullPointerException("Tried to map null to Pet");
        }

        Dog dog = new Dog();

        dog.setId(dogDTO.getPetId());
        dog.setName(dogDTO.getName());
        dog.setBreed(dogDTO.getBreed());
        dog.setSex(dogDTO.getSex());
        dog.setWeight(dogDTO.getWeight());
        dog.setDateOfBirth(dogDTO.getDateOfBirth());
        dog.setDescription(dogDTO.getDescription());
        dog.setSpecialNeeds(dogDTO.getSpecialNeeds());
        if (dogDTO.getOwnerId() > 0) {
            dog.setOwner(new Owner(dogDTO.getOwnerId()));
        } else {
            dog.setOwner(null);
        }
        dog.setStatus(dogDTO.getStatus());

        return dog;
    }

    public DogDTO fromPet(Dog dog) {
        if (dog == null) {
            throw new NullPointerException("Tried to map null to PetDTO");
        }

        DogDTO dogDTO = new DogDTO();

        dogDTO.setPetId(dog.getId());
        dogDTO.setName(dog.getName());
        dogDTO.setBreed(dog.getBreed());
        dogDTO.setSex(dog.getSex());
        dogDTO.setWeight(dog.getWeight());
        dogDTO.setAge(dog.getAge());
        dogDTO.setDateOfBirth(dog.getDateOfBirth());
        dogDTO.setPhoto(dog.getPhoto());
        dogDTO.setMediaType(dog.getMediaType());
        dogDTO.setDescription(dog.getDescription());
        dogDTO.setSpecialNeeds(dog.getSpecialNeeds());
        if(dog.getOwner() != null){
            dogDTO.setOwnerId(dog.getOwner().getId());
        }
        dogDTO.setStatus(dog.getStatus());

        return dogDTO;
    }
}
