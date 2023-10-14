package zhy.votniye.Shelter.service;

import zhy.votniye.Shelter.models.Contact;

import java.util.List;

public interface ContactService {
    Contact create(Contact contact);

    Contact read(Long id);

    Contact update(Long id);

    Contact delete(Long id);

    List<Contact> readAll();
}
