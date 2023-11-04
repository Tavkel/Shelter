package zhy.votniye.Shelter.utils.mappers;

import org.springframework.stereotype.Component;
import zhy.votniye.Shelter.models.DTO.CatDTO;
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.domain.Cat;
import zhy.votniye.Shelter.models.domain.Owner;
import zhy.votniye.Shelter.models.domain.Pet;

@Component
public class CatMapper implements PetMapper<Cat, CatDTO> {

    public Cat toPet(CatDTO catDTO) {
        if (catDTO == null) {
            throw new NullPointerException("Tried to map null to Pet");
        }

        Cat cat = new Cat();

        cat.setId(catDTO.getPetId());
        cat.setName(catDTO.getName());
        cat.setBreed(catDTO.getBreed());
        cat.setSex(catDTO.getSex());
        cat.setWeight(catDTO.getWeight());
        cat.setDateOfBirth(catDTO.getDateOfBirth());
        cat.setDescription(catDTO.getDescription());
        cat.setSpecialNeeds(catDTO.getSpecialNeeds());
        if (catDTO.getOwnerId() > 0) {
            cat.setOwner(new Owner(catDTO.getOwnerId()));
        } else {
            cat.setOwner(null);
        }
        cat.setStatus(catDTO.getStatus());

        return cat;
    }

    public CatDTO fromPet(Cat cat) {
        if (cat == null) {
            throw new NullPointerException("Tried to map null to PetDTO");
        }

        CatDTO catDTO = new CatDTO();

        catDTO.setPetId(cat.getId());
        catDTO.setName(cat.getName());
        catDTO.setBreed(cat.getBreed());
        catDTO.setSex(cat.getSex());
        catDTO.setWeight(cat.getWeight());
        catDTO.setAge(cat.getAge());
        catDTO.setDateOfBirth(cat.getDateOfBirth());
        catDTO.setPhoto(cat.getPhoto());
        catDTO.setMediaType(cat.getMediaType());
        catDTO.setDescription(cat.getDescription());
        catDTO.setSpecialNeeds(cat.getSpecialNeeds());
        if(cat.getOwner() != null){
            catDTO.setOwnerId(cat.getOwner().getId());
        }
        catDTO.setStatus(cat.getStatus());

        return catDTO;
    }
}
