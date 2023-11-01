package zhy.votniye.Shelter.services.interfaces;

import zhy.votniye.Shelter.models.domain.Contact;

import java.util.List;

@Deprecated
public interface ContactService {
    @Deprecated
    Contact create(Contact contact);
@Deprecated
    Contact read(Long id);
@Deprecated
    Contact update(Contact contact);
@Deprecated
    Contact delete(Long id);
@Deprecated
    List<Contact> readAll();
}
