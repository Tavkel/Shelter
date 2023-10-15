package zhy.votniye.Shelter.services.implimentations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zhy.votniye.Shelter.exception.PetAlreadyExistsException;
import zhy.votniye.Shelter.models.domain.Pet;
import zhy.votniye.Shelter.repository.PetRepository;
import zhy.votniye.Shelter.services.interfaces.PetService;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {
    private final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);
    private final PetRepository petRepository;

    private final PetServiceImpl petServiceImpl;

    public PetServiceImpl(PetRepository petRepository,PetServiceImpl petServiceImpl) {
        this.petRepository = petRepository;
        this.petServiceImpl=petServiceImpl;
    }

    @Override
    public Pet create(Pet pet) {
        logger.info("The create method was called with the data " + pet);
        if (petRepository.findByNameAndBreedAndWeight(
                        pet.getName()
                        , pet.getBreed()
                        , pet.getWeight())
                .isPresent()) {
            throw new PetAlreadyExistsException("The database already has this pet");
        }
        logger.info(pet + " - added to the database");
        return petRepository.save(pet);
    }

    @Override
    public Pet read(Long id) {
        logger.info("The read method was called with the data " + id);
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isEmpty()) {
            throw new PetAlreadyExistsException("There is no such pet in the database");
        }
        logger.info("The read method returned the pet from the database" + pet.get());
        return pet.get();
    }

    @Override
    public Pet update(Pet pet) {
        logger.info("The update method was called with the data " + pet);
        if (petRepository.findById(pet.getId()).isEmpty()) {
            throw new PetAlreadyExistsException("There is no such pet in the database");
        }
        logger.info("The update method returned the pet from the database" + pet);
        return petRepository.save(pet);
    }

    @Override
    public Pet delete(Long id) {
        logger.info("The delete method was called with the data " + id);
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isEmpty()) {
            throw new PetAlreadyExistsException("There is no such pet in the database");
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
    public List<Pet> readAllPagination(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 5);
        return petRepository.findAll(pageRequest).getContent();
    }

    @Override
    public void savePetPhoto(long id, MultipartFile file) throws IOException {
        logger.debug(String.format("Attempting to create a record for photo for pet %d", id));

        try {
            Pet pet = petServiceImpl.read(id);
        } catch (NoSuchElementException ex) {
            if (!ex.getMessage().equals(avatarNotFoundMessage)) {
                logger.warn(String.format("Encountered an error while creating a preview for photo for pet %d", id));
                logger.warn(ex.getMessage());
                throw ex;
            }
            Pet pet = new Pet();
            pet.setId(id);

        }

        logger.debug("Attempting to write original image to file");
        var filePath = writeAvatarToFile(studentId, file);
        avatarSetUp(file, avatar, filePath);

        petRepository.saveAndFlush(pet);
        logger.debug("Avatar saved");
    }


}
