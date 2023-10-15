package zhy.votniye.Shelter.services.implimentations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.exception.OwnerAlreadyExistsException;
import zhy.votniye.Shelter.models.domain.Owner;
import zhy.votniye.Shelter.repository.OwnerRepository;
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
        logger.info(owner + " - added to the database");
        return ownerRepository.save(owner);
    }

    @Override
    public Owner read(Long id) {
        logger.info("The read method was called with the data " + id);
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) {
            throw new NoSuchElementException("Owner not found");
        }
        logger.info("The read method returned the owner from the database" + owner.get());
        return owner.get();
    }

    @Override
    public Owner update(Owner owner) {
        logger.info("The update method was called with the data " + owner);
        if (ownerRepository.findById(owner.getId()).isEmpty()) {
            throw new NoSuchElementException("Owner not found");
        }
        logger.info("The update method returned the owner from the database" + owner);
        return ownerRepository.save(owner);
    }

    @Override
    public Owner delete(Long id) {
        logger.info("The delete method was called with the data " + id);
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) {
            throw new NoSuchElementException("Owner not found");
        }
        logger.info("The  method returned the owner from the database" + owner.get());
        return null;
    }

    @Override
    public List<Owner> readAll() {
        logger.info("The ReadAll method is called");
        return ownerRepository.findAll();
    }
}
