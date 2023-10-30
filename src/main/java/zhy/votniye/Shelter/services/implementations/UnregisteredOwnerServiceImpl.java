package zhy.votniye.Shelter.services.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.models.domain.UnregisteredOwner;
import zhy.votniye.Shelter.repository.UnregisteredOwnerRepository;
import zhy.votniye.Shelter.services.interfaces.UnregisteredOwnerService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UnregisteredOwnerServiceImpl implements UnregisteredOwnerService {

    private final Logger logger = LoggerFactory.getLogger(UnregisteredOwnerServiceImpl.class);


    private final UnregisteredOwnerRepository unregisteredOwnerRepository;

    public UnregisteredOwnerServiceImpl(UnregisteredOwnerRepository unregisteredOwnerRepository){
        this.unregisteredOwnerRepository=unregisteredOwnerRepository;
    }


    @Override
    @Cacheable("create")
    public void  create(UnregisteredOwner unregisteredOwner) {

        logger.debug("The create method was called with the data " + unregisteredOwner);

         unregisteredOwnerRepository.save(unregisteredOwner);
    }

    @Override
    @Cacheable("read")
    public Optional<UnregisteredOwner> read(long chatId) {

        logger.debug("The read method was called with the data " + chatId);

        return unregisteredOwnerRepository.findById(chatId);
    }

    @Override
    @Cacheable("update")
    public void  update(UnregisteredOwner unregisteredOwner) {

        logger.debug("The read method was called with the data " + unregisteredOwner);

        if(unregisteredOwnerRepository.findById(unregisteredOwner.getChatId()).isEmpty()){
            throw new NoSuchElementException("UnregisteredOwner not found");
        }

         unregisteredOwnerRepository.save(unregisteredOwner);
    }

    @Override
    @Cacheable("delete")
    public void delete(long chatId) {

        logger.debug("The delete method was called with the data " + chatId);

        Optional<UnregisteredOwner> unregisteredOwner =
                unregisteredOwnerRepository.findById(chatId);

        if(unregisteredOwner.isEmpty()) {
            throw new NoSuchElementException("UnregisteredOwner not found");
        }

        unregisteredOwnerRepository.delete(unregisteredOwner.get());

    }

}
