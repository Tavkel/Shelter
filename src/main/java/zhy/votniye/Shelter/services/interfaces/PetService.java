package zhy.votniye.Shelter.services.interfaces;

import zhy.votniye.Shelter.models.domain.Pet;

import java.util.List;

public interface PetService {
    Pet create(Pet pet);

    Pet read(Long id);

    Pet update(Pet pet);

    Pet delete(Long id);

    List<Pet> readAll();

    List<Pet> readAllPagination(int pageNumber);
}
