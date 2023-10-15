package zhy.votniye.Shelter.mapper;

import zhy.votniye.Shelter.models.Contact;
import zhy.votniye.Shelter.models.DTO.ContactDTO;
import zhy.votniye.Shelter.models.DTO.OwnerDTO;
import zhy.votniye.Shelter.models.Owner;

public class ContactMapper {

    public Contact toContact(ContactDTO contactDTO) {
        if (contactDTO == null) {
            return null;
        }

        Contact contact = new Contact();


        contact.setId(contactDTO.getContactId());
        contact.setPhone(contactDTO.getPhone());
        contact.setEmail(contactDTO.getEmail());
        contact.setAddress(contactDTO.getAddress());
        contact.setComment(contactDTO.getComment());


        return contact;
    }

    public ContactDTO fromContact(Contact contact) {
        if (contact == null) {
            return null;
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
