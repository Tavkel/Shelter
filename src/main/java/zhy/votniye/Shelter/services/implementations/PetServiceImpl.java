package zhy.votniye.Shelter.services.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zhy.votniye.Shelter.exception.PetAlreadyExistsException;
import zhy.votniye.Shelter.helpers.PhotoCompression;
import zhy.votniye.Shelter.models.domain.Pet;
import zhy.votniye.Shelter.repository.PetRepository;
import zhy.votniye.Shelter.services.interfaces.PetService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class PetServiceImpl implements PetService {
    private final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);
    private final PetRepository petRepository;

    private final PhotoCompression photoCompression;

    private final String petPhotoNotFoundMessage = "Pet does not have an photo.";

    private final String petPhotoDirectory;

    public PetServiceImpl(PetRepository petRepository,PhotoCompression photoCompression,
                          @Value("petPhoto-directory") String petPhotoDirectory) {
        this.petRepository = petRepository;
        this.photoCompression = photoCompression;
        this.petPhotoDirectory = petPhotoDirectory;

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
    public Pet getPetPhotoPreview(long id) {
        read(id);
        logger.debug(String.format("Getting photo for pet %d", id));
        return petRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(petPhotoNotFoundMessage));
    }

    @Override
    public void savePetPhoto(long id, MultipartFile file) throws IOException {
        logger.debug(String.format("Attempting to create a record for photo for pet %d", id));
        Pet pet;
        try {
             pet = getPetPhotoPreview(id);
        } catch (NoSuchElementException ex) {
            if (!ex.getMessage().equals(petPhotoNotFoundMessage)) {
                logger.warn(String.format("Encountered an error while creating a preview for photo for pet %d", id));
                logger.warn(ex.getMessage());
                throw ex;
            }
            pet = new Pet();
            pet.setId(id);

        }

        logger.debug("Attempting to write original image to file");
        var pathToFile = writePetPhotoToFile(id, file);
        avatarSetUp(file, pet, pathToFile);

        petRepository.saveAndFlush(pet);
        logger.debug("PetPhoto saved");
    }

    private void avatarSetUp(MultipartFile file, Pet pet, Path filePath) throws IOException{
        logger.debug("Setting up avatar properties");
        pet.setPathToFile(filePath.toString());
        pet.setPhoto(photoCompression.generatePreview(filePath));
        logger.debug("PetPhoto properties set");
    }

    private Path writePetPhotoToFile(long id, MultipartFile file) throws IOException {
        Path filePath = Path.of(petPhotoDirectory, id + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        logger.debug(String.format("PetPhoto successfully written to file %s", filePath));
        return filePath;
    }
    private String getExtension(String fileName) {
        if (fileName == null) return "";
        int lastDot = fileName.lastIndexOf(".");
        if (lastDot == -1) return "";
        return fileName.substring(lastDot);
    }


}
