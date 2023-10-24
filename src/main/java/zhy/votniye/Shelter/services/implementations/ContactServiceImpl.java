package zhy.votniye.Shelter.services.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.exceptions.ContactAlreadyExistsException;
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

    /**
     * The method creates a contact and save it in the database
     * The repository method is used for saving {@link ContactRepository#save(Object)}
     *
     * @param contact the pet being created
     * @return a saved contact
     * @throws ContactAlreadyExistsException The database already has this contact
     * @see ContactRepository#findByPhoneAndTelegramChatId(long, long)
     */
    @Override
    public Contact create(Contact contact) {
        if (contact.getOwner().getId() == 0) {
            var o = ownerService.create(contact.getOwner());
            contact.setId(o.getId());

            contact.setOwner(null);
        }
        return contactRepository.save(contact);
    }

    /**
     * Search for a contact by ID in the database.
     * The repository method is used {@link ContactRepository#findById(Object)}
     *
     * @param id cannot be null
     * @return the founds contact
     * @throws NoSuchElementException Contact not found
     */
    @Override
    public Contact read(Long id) {
        logger.debug("The read method was called with the data " + id);
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isEmpty()) {
            throw new NoSuchElementException("Contact not found");
        }
        return contact.get();
    }

    /**
     * The method recreates the contact by searching for an identifier in the database
     * To find a contact, use the repository method {@link ContactRepository#findById(Object)}
     * To recreate a contact, use the repository method {@link ContactRepository#save(Object)}
     *
     * @param contact rewritable contact
     * @return new contact
     * @throws NoSuchElementException Contact not found
     */
    @Override
    public Contact update(Contact contact) {
        logger.debug("The update method was called with the data " + contact);
        if (contactRepository.findById(contact.getId()).isEmpty()) {
            throw new NoSuchElementException("Contact not found");
        }
        return contactRepository.save(contact);
    }

    /**
     * The method searches for the contact ID in the database and deletes it.
     * To find a contact, use the repository method {@link ContactRepository#findById(Object)}
     * To delete information, use the repository method {@link ContactRepository#delete(Object)}
     *
     * @param id - cannot be null
     * @return delete contact
     * @throws NoSuchElementException Contact not found
     */
    @Override
    public Contact delete(Long id) {
        logger.debug("The delete method was called with the data " + id);
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isEmpty()) {
            throw new NoSuchElementException("Contact not found");
        }
        return contact.get();
    }

    /**
     * The method shows all the contacts stored in the database.
     * The repository method is used {@link ContactRepository#findAll()}
     *
     * @return all contacts
     */
    @Override
    public List<Contact> readAll() {
        logger.debug("The ReadAll method is called");
        return contactRepository.findAll();
    }
}
