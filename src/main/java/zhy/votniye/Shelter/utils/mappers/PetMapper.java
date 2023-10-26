package zhy.votniye.Shelter.utils.mappers;

import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.domain.Pet;

public interface PetMapper<T extends Pet, R extends PetDTO> {
    T toPet(R petDto);
    R fromPet(T pet);
}
