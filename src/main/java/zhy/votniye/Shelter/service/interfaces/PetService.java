package zhy.votniye.Shelter.service.interfaces;

import zhy.votniye.Shelter.models.Pet;

import java.util.List;

public interface PetService {
    Pet create(Pet pet);

    Pet read(Long id);

    Pet update(Pet pet);

    Pet delete(Long id);

    List<Pet> readAll();
}
