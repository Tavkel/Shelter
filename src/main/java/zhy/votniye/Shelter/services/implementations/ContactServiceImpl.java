package zhy.votniye.Shelter.services.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.models.domain.Contact;
import zhy.votniye.Shelter.repository.ContactRepository;
import zhy.votniye.Shelter.services.interfaces.ContactService;
import zhy.votniye.Shelter.services.interfaces.OwnerService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class ContactServiceImpl implements ContactService {
    private final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);
    private final ContactRepository contactRepository;
    private final OwnerService ownerService;

    public ContactServiceImpl(ContactRepository contactRepository, OwnerService ownerService) {
        this.contactRepository = contactRepository;
        this.ownerService = ownerService;
    }

    @Override
    public Contact create(Contact contact) {
        if (contact.getOwner().getId() == 0) {
            var o = ownerService.create(contact.getOwner());
            contact.setId(o.getId());

            contact.setOwner(null);
        }
        return contactRepository.save(contact);
    }

    @Override
    public Contact read(Long id) {
        logger.info("The read method was called with the data " + id);
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isEmpty()) {
            throw new NoSuchElementException("Contact not found");
        }
        logger.info("The read method returned the contact from the database" + contact.get());
        return contact.get();
    }

    @Override
    public Contact update(Contact contact) {
        logger.info("The update method was called with the data " + contact);
        if (contactRepository.findById(contact.getOwner().getId()).isEmpty()) {
            throw new NoSuchElementException("Contact not found");
        }
        logger.info("The update method returned the contact from the database" + contact);
        return contactRepository.save(contact);
    }

    @Override
    public Contact delete(Long id) {
        logger.info("The delete method was called with the data " + id);
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isEmpty()) {
            throw new NoSuchElementException("Contact not found");
        }
        logger.info("The  method returned the contact from the database" + contact.get());
        return null;
    }

    @Override
    public List<Contact> readAll() {
        logger.info("The ReadAll method is called");
        return contactRepository.findAll();
    }
}
