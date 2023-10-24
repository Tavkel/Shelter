package zhy.votniye.Shelter.services.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.models.domain.AdoptionRequest;
import zhy.votniye.Shelter.repository.AdoptionRequestRepository;
import zhy.votniye.Shelter.services.interfaces.AdoptionRequestService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdoptionRequestServiceImpl implements AdoptionRequestService {
    private final Logger logger = LoggerFactory.getLogger(AdoptionRequestServiceImpl.class);
    private final AdoptionRequestRepository adoptionRequestRepository;

    public AdoptionRequestServiceImpl(AdoptionRequestRepository adoptionRequestRepository) {
        this.adoptionRequestRepository = adoptionRequestRepository;
    }

    /**
     * The method creates an adoption and save it in the database
     * The repository method is used for saving {@link AdoptionRequestRepository#save(Object)}
     *
     * @param adoption the adoption being created
     * @return a saved adoption
     */
    @Override
    public AdoptionRequest create(AdoptionRequest adoption) {
        logger.debug("The create method was called with the data " + adoption);
        return adoptionRequestRepository.save(adoption);
    }

    /**
     * Search for an adoption by ID in the database.
     * The repository method is used {@link AdoptionRequestRepository#findById(Object)}
     *
     * @param id cannot be null
     * @return the founds adoption
     * @throws NoSuchElementException Adoption not found
     */
    @Override
    public AdoptionRequest read(Long id) {
        logger.debug("The read method was called with the data " + id);
        Optional<AdoptionRequest> adoption = adoptionRequestRepository.findById(id);
        if (adoption.isEmpty()) {
            throw new NoSuchElementException("Adoption request not found");
        }
        return adoption.get();
    }

    /**
     * The method recreates the adoption by searching for an identifier in the database
     * To find an adoption, use the repository method {@link AdoptionRequestRepository#findById(Object)}
     * To recreate an adoption, use the repository method {@link AdoptionRequestRepository#save(Object)}
     *
     * @param adoption rewritable adoption
     * @return new adoption
     * @throws NoSuchElementException Adoption not found
     */
    @Override
    public AdoptionRequest update(AdoptionRequest adoption) {
        logger.debug("The update method was called with the data " + adoption);
        if (adoptionRequestRepository.findById(adoption.getId()).isEmpty()) {
            throw new NoSuchElementException("Adoption request not found");
        }
        return adoptionRequestRepository.save(adoption);
    }

    /**
     * The method searches for the adoption ID in the database and deletes it.
     * To find an adoption, use the repository method {@link AdoptionRequestRepository#findById(Object)}
     * To delete information, use the repository method {@link AdoptionRequestRepository#delete(Object)}
     *
     * @param id - cannot be null
     * @return delete adoption
     * @throws NoSuchElementException Adoption not found
     */
    @Override
    public AdoptionRequest delete(Long id) {
        logger.debug("The delete method was called with the data " + id);
        Optional<AdoptionRequest> adoption = adoptionRequestRepository.findById(id);
        if (adoption.isEmpty()) {
            throw new NoSuchElementException("Adoption request not found");
        }
        return adoption.get();
    }

    /**
     * The method shows all the adoption stored in the database.
     * The repository method is used {@link AdoptionRequestRepository#findAll()}
     *
     * @return all adoption
     */
    @Override
    public List<AdoptionRequest> readAll() {
        logger.debug("The ReadAll method is called");
        return adoptionRequestRepository.findAll();
    }
}
