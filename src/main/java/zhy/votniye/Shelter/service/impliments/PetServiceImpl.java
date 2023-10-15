package zhy.votniye.Shelter.service.impliments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.exception.PetAlreadyAddException;
import zhy.votniye.Shelter.models.Pet;
import zhy.votniye.Shelter.repository.PetRepository;
import zhy.votniye.Shelter.service.interfaces.PetService;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {
    private final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet create(Pet pet) {
        logger.info("The create method was called with the data " + pet);
        if (petRepository.findByNameAndBreedAndWeight(
                        pet.getName()
                        , pet.getBreed()
                        , pet.getWeight())
                .isPresent()) {
            throw new PetAlreadyAddException("The database already has this pet");
        }
        logger.info(pet + " - added to the database");
        return petRepository.save(pet);
    }

    @Override
    public Pet read(Long id) {
        logger.info("The read method was called with the data " + id);
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isEmpty()) {
            throw new PetAlreadyAddException("There is no such pet in the database");
        }
        logger.info("The read method returned the pet from the database" + pet.get());
        return pet.get();
    }

    @Override
    public Pet update(Pet pet) {
        logger.info("The update method was called with the data " + pet);
        if (petRepository.findById(pet.getId()).isEmpty()) {
            throw new PetAlreadyAddException("There is no such pet in the database");
        }
        logger.info("The update method returned the pet from the database" + pet);
        return petRepository.save(pet);
    }

    @Override
    public Pet delete(Long id) {
        logger.info("The delete method was called with the data " + id);
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isEmpty()) {
            throw new PetAlreadyAddException("There is no such pet in the database");
        }
        logger.info("The  method returned the pet from the database" + pet.get());
        return null;
    }

    @Override
    public List<Pet> readAll() {
        logger.info("The ReadAll method is called");
        return petRepository.findAll();
    }

    @Override
    public List<Pet> readAllPagination(Integer pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 5);
        return petRepository.findAll(pageRequest).getContent();
    }
}
