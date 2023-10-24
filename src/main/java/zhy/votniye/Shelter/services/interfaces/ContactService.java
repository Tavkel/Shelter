package zhy.votniye.Shelter.services.interfaces;

import zhy.votniye.Shelter.models.domain.Contact;

import java.util.List;

public interface ContactService {
    Contact create(Contact contact);

    Contact read(Long id);

    Contact update(Contact contact);

    Contact delete(Long id);

    List<Contact> readAll();
}
