package zhy.votniye.Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhy.votniye.Shelter.models.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
