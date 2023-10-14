package zhy.votniye.Shelter.service.impliments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zhy.votniye.Shelter.models.AdoptionRequest;
import zhy.votniye.Shelter.repository.AdoptionRequestRepository;
import zhy.votniye.Shelter.service.interfaces.AdoptionRequestService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class AdoptionRequestServiceImpl implements AdoptionRequestService {
    private final Logger logger = LoggerFactory.getLogger(AdoptionRequestServiceImpl.class);
    private final AdoptionRequestRepository adoptionRequestRepository;

    public AdoptionRequestServiceImpl(AdoptionRequestRepository adoptionRequestRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;

    }

    @Override
    public AdoptionRequest create(AdoptionRequest adoption) {
        logger.info("The create method was called with the data " + adoption);

        logger.info(adoption + " - added to the database");
        return adoptionRequestRepository.save(adoption);
    }

    @Override
    public AdoptionRequest read(Long id) {
        logger.info("The read method was called with the data " + id);
        Optional<AdoptionRequest> adoption = adoptionRequestRepository.findById(id);
        if (adoption.isEmpty()) {
            throw new NoSuchElementException("Adoption not found");
        }
        logger.info("The read method returned the adoption from the database" + adoption.get());
        return adoption.get();
    }

    @Override
    public AdoptionRequest update(AdoptionRequest adoption) {
        logger.info("The update method was called with the data " + adoption);
        if (adoptionRequestRepository.findById(adoption.getId()).isEmpty()) {
            throw new NoSuchElementException("Adoption not found");
        }
        logger.info("The update method returned the adoption from the database" + adoption);
        return adoptionRequestRepository.save(adoption);
    }

    @Override
    public AdoptionRequest delete(Long id) {
        logger.info("The delete method was called with the data " + id);
        Optional<AdoptionRequest> adoption = adoptionRequestRepository.findById(id);
        if (adoption.isEmpty()) {
            throw new NoSuchElementException("Adoption not found");
        }
        logger.info("The  method returned the adoption from the database" + adoption.get());
        return null;
    }

    @Override
    public List<AdoptionRequest> readAll() {
        logger.info("The ReadAll method is called");
        return adoptionRequestRepository.findAll();
    }
}
