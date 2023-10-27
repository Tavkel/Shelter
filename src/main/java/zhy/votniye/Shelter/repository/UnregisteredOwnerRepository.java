package zhy.votniye.Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhy.votniye.Shelter.models.domain.UnregisteredOwner;

public interface UnregisteredOwnerRepository extends JpaRepository<UnregisteredOwner, Long> {

}
