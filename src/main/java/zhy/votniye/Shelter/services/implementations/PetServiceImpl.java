package zhy.votniye.Shelter.services.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;
import zhy.votniye.Shelter.exceptions.PetAlreadyExistsException;
import zhy.votniye.Shelter.models.domain.Owner;
import zhy.votniye.Shelter.services.interfaces.OwnerService;
import zhy.votniye.Shelter.utils.PhotoCompression;
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


public abstract class PetServiceImpl<T extends Pet> implements PetService {
    private final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);
    protected final PetRepository petRepository;
    private final PhotoCompression photoCompression;
    private final OwnerService ownerService;
    private final String petPhotoNotFoundMessage = "Pet does not have an photo.";
    @Value("path-to-photo-folder")
    private String petPhotoDirectory;

    public PetServiceImpl(PetRepository petRepository, PhotoCompression photoCompression, OwnerService ownerService) {
        this.petRepository = petRepository;
        this.photoCompression = photoCompression;
        this.ownerService = ownerService;
    }

    /**
     * The method creates a Pet and save it in the database
     * The repository method is used for saving {@link PetRepository#save(Object)}
     *
     * @param pet the pet being created
     * @return a saved pet
     * @throws PetAlreadyExistsException The database already has this pet
     * @see PetRepository#findByNameAndBreedAndWeight(String, String, float)
     */
    @Override
    public T create(Pet pet) {
        logger.debug("The create method was called with the data " + pet);
        if (petRepository.findByNameAndBreedAndWeight(
                pet.getName(),
                pet.getBreed(),
                pet.getWeight()).isPresent()) {
            throw new PetAlreadyExistsException("The database already has this pet");
        }
        return (T) petRepository.save(pet);
    }

    /**
     * Search for a pet by ID in the database.
     * The repository method is used {@link PetRepository#findById(Object)}
     *
     * @param id cannot be null
     * @return the founds pet
     * @throws NoSuchElementException There is no pet with this id in the database
     */
    @Override
    public T read(Long id) {
        logger.debug("The read method was called with the data " + id);
        Optional<T> pet = petRepository.findById(id);
        if (pet.isEmpty()) {
            throw new NoSuchElementException("There is no pet with this id in the database");
        }
        return pet.get();
    }

    /**
     * The method recreates the pet by searching for an identifier in the database
     * To find a pet, use the repository method {@link PetRepository#findById(Object)}
     * To recreate a pet, use the repository method {@link PetRepository#save(Object)}
     *
     * @param pet rewritable pet
     * @return new pet
     * @throws NoSuchElementException There is no pet with this id in the database
     */
    @Override
    public T update(Pet pet) {
        logger.debug("The update method was called with the data " + pet);

        Optional<T> check = petRepository.findById(pet.getId());
        if (check.isEmpty()) {
            throw new NoSuchElementException("There is no pet with this id in the database");
        }
        var existingPet = check.get();

        Owner owner = null;
        if (pet.getOwner() != null) {
            owner = ownerService.read(pet.getOwner().getId());
        }

        pet.setOwner(owner);
        pet.setMediaType(existingPet.getMediaType());
        pet.setFileSize(existingPet.getFileSize());
        pet.setPathToFile(existingPet.getPathToFile());
        pet.setPhoto(existingPet.getPhoto());

        return (T) petRepository.save(pet);
    }

    /**
     * The method searches for the pet ID in the database and deletes it.
     * To find a pet, use the repository method {@link PetRepository#findById(Object)}
     * To delete information, use the repository method {@link PetRepository#delete(Object)}
     *
     * @param id - cannot be null
     * @return delete pet
     * @throws NoSuchElementException There is no pet with this id in the database
     */
    @Override
    public T delete(Long id) {
        logger.debug("The delete method was called with the data " + id);
        Optional<T> pet = petRepository.findById(id);
        if (pet.isEmpty()) {
            throw new NoSuchElementException("There is no pet with this id in the database");
        }
        petRepository.delete(pet.get());
        return pet.get();
    }

    /**
     * The method shows all the pets stored in the database.
     * The repository method is used {@link PetRepository#findAll()}
     *
     * @return all pets
     */
    @Override
    public List<T> readAll() {
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
    public List<T> readAllPagination(int pageNumber) {
        logger.debug("The method shows pets of 5 pieces per 1 page");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 5);
        return petRepository.findAll(pageRequest).getContent();
    }

    /**
     * Gets pet from db by id, calls for methods to write received image to drive, compress image for storing it in db,
     * setting photo related fields in pet object. Saves resulting pet to db
     *
     * @param id
     * @param file
     * @throws IOException
     * @see #petPhotoSetUp(MultipartFile, Pet, Path)
     * @see #writePetPhotoToFile(long, MultipartFile)
     */
    @Override
    public void savePetPhoto(long id, MultipartFile file) throws IOException {
        logger.info(String.format("Attempting to create a record for photo for pet %d", id));
        Optional<T> pet;
        pet = petRepository.findById(id);//.orElseThrow(() -> new NoSuchElementException("pet not found"));
        if (pet.isEmpty()) throw new NoSuchElementException("pet not found");

        logger.debug("Attempting to write original image to file");
        var pathToFile = writePetPhotoToFile(id, file);
        petPhotoSetUp(file, pet.get(), pathToFile);

        petRepository.saveAndFlush(pet.get());
        logger.debug("Pet photo saved");
    }

    /**
     * Sets all photo related fields of pet object
     *
     * @param file
     * @param pet
     * @param filePath
     * @throws IOException
     */
    private void petPhotoSetUp(MultipartFile file, Pet pet, Path filePath) throws IOException {
        logger.debug("Setting up photo properties");
        pet.setPathToFile(filePath.toString());
        pet.setPhoto(photoCompression.generatePreview(filePath));
        pet.setFileSize(file.getSize());
        pet.setMediaType(file.getContentType());
        logger.debug("Pet photo properties set");
    }

    /**
     * Writes photo to drive and returns path to it
     *
     * @param id
     * @param file
     * @return path to the written file
     * @throws IOException
     */
    private Path writePetPhotoToFile(long id, MultipartFile file) throws IOException {
        Path filePath = Path.of(petPhotoDirectory == null ?
                        "./photo" :
                        petPhotoDirectory + "/" + this.getClass().getSimpleName().substring(0, 3),
                id + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        logger.debug(String.format("Pet photo successfully written to file %s", filePath));
        return filePath;
    }

    /**
     * @param fileName
     * @return file's extension
     */
    private String getExtension(String fileName) {
        if (fileName == null) return "";
        int lastDot = fileName.lastIndexOf(".");
        if (lastDot == -1) return "";
        return fileName.substring(lastDot);
    }
}
