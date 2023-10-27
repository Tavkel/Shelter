package zhy.votniye.Shelter.utils.mappers;

import zhy.votniye.Shelter.models.domain.Contact;
import zhy.votniye.Shelter.models.DTO.ContactDTO;

@Deprecated
public class ContactMapper {

    public static Contact toContact(ContactDTO contactDTO) {
        if (contactDTO == null) {
            throw new NullPointerException("Tried to map null to Contact");
        }

        Contact contact = new Contact();


        contact.setId(contactDTO.getContactId());
        contact.setPhone(contactDTO.getPhone());
        contact.setEmail(contactDTO.getEmail());
        contact.setAddress(contactDTO.getAddress());
        contact.setComment(contactDTO.getComment());


        return contact;
    }

    public static ContactDTO fromContact(Contact contact) {
        if (contact == null) {
            throw new NullPointerException("Tried to map null to ContactDTO");
        }

        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setContactId(contact.getId());
        contactDTO.setPhone(contact.getPhone());
        contactDTO.setEmail(contact.getEmail());
        contactDTO.setAddress(contact.getAddress());
        contactDTO.setComment(contact.getComment());

        return contactDTO;
    }
}
