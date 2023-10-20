package zhy.votniye.Shelter.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.exception.OwnerAlreadyExistsException;
import zhy.votniye.Shelter.exception.PetAlreadyExistsException;
import zhy.votniye.Shelter.models.domain.Owner;
import zhy.votniye.Shelter.repository.OwnerRepository;
import zhy.votniye.Shelter.repository.PetRepository;
import zhy.votniye.Shelter.services.interfaces.OwnerService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class OwnerServiceImpl implements OwnerService {
    private final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);
    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;

    }
    /**
     * The method creates an Owner and save it in the database
     * The repository method is used for saving {@link JpaRepository#save(Object)}
     *
     * @param owner the pet being created
     * @return a saved owner
     * @throws OwnerAlreadyExistsException The database already has this owner
     * @see OwnerRepository#findByFirstNameAndLastNameAndMiddleName(String, String, String)
     */
    @Override
    public Owner create(Owner owner) {
        logger.info("The create method was called with the data " + owner);
        if (ownerRepository.findByFirstNameAndLastNameAndMiddleName(
                owner.getFirstName()
                ,owner.getLastName()
                ,owner.getMiddleName())
                .isPresent()) {
            throw new OwnerAlreadyExistsException("The database already has this owner");
        }

        return ownerRepository.save(owner);
    }
    /**
     * Search for an owner by ID in the database.
     * The repository method is used {@link JpaRepository#findById(Object)}
     *
     * @param id  cannot be null
     * @throws NoSuchElementException Owner not found
     * @return the founds owner
     *
     */
    @Override
    public Owner read(Long id) {
        logger.debug("The read method was called with the data " + id);
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) {
            throw new NoSuchElementException("Owner not found");
        }
        return owner.get();
    }
    /**
     * The method recreates the owner by searching for an identifier in the database
     * To find an owner, use the repository method {@link JpaRepository#findById(Object)}
     * To recreate an owner, use the repository method {@link JpaRepository#save(Object)}
     *
     * @param owner rewritable owner
     * @throws NoSuchElementException Owner not found
     * @return new pet
     */
    @Override
    public Owner update(Owner owner) {
        logger.debug("The update method was called with the data " + owner);
        if (ownerRepository.findById(owner.getId()).isEmpty()) {
            throw new NoSuchElementException("Owner not found");
        }
        return ownerRepository.save(owner);
    }
    /**
     * The method searches for the owner ID in the database and deletes it.
     * To find an owner, use the repository method {@link JpaRepository#findById(Object)}
     * To delete information, use the repository method {@link JpaRepository#delete(Object)}
     *
     *
     * @param id - cannot be null
     * @throws NoSuchElementException Owner not found
     * @return delete owner
     */
    @Override
    public Owner delete(Long id) {
        logger.debug("The delete method was called with the data " + id);
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) {
            throw new NoSuchElementException("Owner not found");
        }
        return owner.get();
    }
    /**
     * The method shows all the owners stored in the database.
     * The repository method is used {@link JpaRepository#findAll()}
     *
     *
     * @return all owners
     */
    @Override
    public List<Owner> readAll() {
        logger.debug("The ReadAll method is called");
        return ownerRepository.findAll();
    }
}
