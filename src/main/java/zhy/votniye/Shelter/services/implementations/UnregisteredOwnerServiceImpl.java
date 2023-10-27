package zhy.votniye.Shelter.services.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.models.domain.UnregisteredOwner;
import zhy.votniye.Shelter.repository.UnregisteredOwnerRepository;
import zhy.votniye.Shelter.services.interfaces.UnregisteredOwnerService;
@Service
public class UnregisteredOwnerServiceImpl implements UnregisteredOwnerService {

    private final Logger logger = LoggerFactory.getLogger(UnregisteredOwnerServiceImpl.class);

    private final UnregisteredOwnerRepository unregisteredOwnerRepository;

    public UnregisteredOwnerServiceImpl(UnregisteredOwnerRepository unregisteredOwnerRepository){
        this.unregisteredOwnerRepository=unregisteredOwnerRepository;
    }

    @Override
    public UnregisteredOwner create(UnregisteredOwner unregisteredOwner) {
        logger.debug("The create method was called with the data " + unregisteredOwner);
        return unregisteredOwnerRepository.save(unregisteredOwner);
    }


}
