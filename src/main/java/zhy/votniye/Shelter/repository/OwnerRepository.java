package zhy.votniye.Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhy.votniye.Shelter.models.Contact;
import zhy.votniye.Shelter.models.Owner;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByFirstNameAndLastNameAndMiddleName(
            String firstName, String lastName, String middleName);
}
