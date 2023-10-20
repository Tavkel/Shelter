package zhy.votniye.Shelter.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.exception.PetAlreadyExistsException;
import zhy.votniye.Shelter.models.domain.Pet;
import zhy.votniye.Shelter.repository.PetRepository;
import zhy.votniye.Shelter.services.interfaces.PetService;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {
    private final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * The method creates a Pet and save it in the database
     * The repository method is used for saving {@link JpaRepository#save(Object)}
     *
     * @param pet the pet being created
     * @return a saved pet
     * @throws PetAlreadyExistsException The database already has this pet
     * @see PetRepository#findByNameAndBreedAndWeight(String, String, float)
     */
    @Override
    public Pet create(Pet pet) {
        logger.debug("The create method was called with the data " + pet);
        if (petRepository.findByNameAndBreedAndWeight(
                        pet.getName()
                        , pet.getBreed()
                        , pet.getWeight())
                .isPresent()) {
            throw new PetAlreadyExistsException("The database already has this pet");
        }
        return petRepository.save(pet);
    }

    /**
     * Search for a pet by ID in the database.
     * The repository method is used {@link JpaRepository#findById(Object)}
     *
     * @param id cannot be null
     * @return the founds pet
     * @throws EntityNotFoundException There is no pet with this id in the database
     */
    @Override
    public Pet read(Long id) {
        logger.debug("The read method was called with the data " + id);
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isEmpty()) {
            throw new EntityNotFoundException("There is no pet with this id in the database");
        }
        return pet.get();
    }

    /**
     * The method recreates the pet by searching for an identifier in the database
     * To find a pet, use the repository method {@link JpaRepository#findById(Object)}
     * To recreate a pet, use the repository method {@link JpaRepository#save(Object)}
     *
     * @param pet rewritable pet
     * @return new pet
     * @throws EntityNotFoundException There is no pet with this id in the database
     */
    @Override
    public Pet update(Pet pet) {
        logger.debug("The update method was called with the data " + pet);
        if (petRepository.findById(pet.getId()).isEmpty()) {
            throw new EntityNotFoundException("There is no pet with this id in the database");
        }

        return petRepository.save(pet);
    }

    /**
     * The method searches for the pet ID in the database and deletes it.
     * To find a pet, use the repository method {@link JpaRepository#findById(Object)}
     * To delete information, use the repository method {@link JpaRepository#delete(Object)}
     *
     * @param id - cannot be null
     * @return delete pet
     * @throws EntityNotFoundException There is no pet with this id in the database
     */
    @Override
    public Pet delete(Long id) {
        logger.debug("The delete method was called with the data " + id);
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isEmpty()) {
            throw new EntityNotFoundException("There is no pet with this id in the database");
        }
        return pet.get();
    }

    /**
     * The method shows all the pets stored in the database.
     * The repository method is used {@link JpaRepository#findAll()}
     *
     * @return all pets
     */
    @Override
    public List<Pet> readAll() {
        logger.debug("The ReadAll method is called");
        return petRepository.findAll();
    }

    /**
     * The method shows pets of 5 pieces per 1 page
     *
     * @param pageNumber number of issued pets per 1 page
     * @return 5 pets
     */
    @Override
    public List<Pet> readAllPagination(int pageNumber) {
        logger.debug("The method shows pets of 5 pieces per 1 page");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 5);
        return petRepository.findAll(pageRequest).getContent();
    }
}
