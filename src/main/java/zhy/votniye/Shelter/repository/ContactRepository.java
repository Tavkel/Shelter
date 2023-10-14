package zhy.votniye.Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhy.votniye.Shelter.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
