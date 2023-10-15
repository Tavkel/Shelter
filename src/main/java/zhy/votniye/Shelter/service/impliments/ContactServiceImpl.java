package zhy.votniye.Shelter.service.impliments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.exception.ContactAlreadyAddException;
import zhy.votniye.Shelter.models.Contact;
import zhy.votniye.Shelter.repository.ContactRepository;
import zhy.votniye.Shelter.service.interfaces.ContactService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class ContactServiceImpl implements ContactService {
    private final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;

    }

    @Override
    public Contact create(Contact contact) {
        logger.info("The create method was called with the data " + contact);
        if (contactRepository.findByPhoneAndTelegramChatId(contact.getPhone(),contact.getTelegramChatId())
                .isPresent()) {
            throw new ContactAlreadyAddException("The database already has this contact");
        }
        logger.info(contact + " - added to the database");
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
        if (contactRepository.findById(contact.getId()).isEmpty()) {
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
