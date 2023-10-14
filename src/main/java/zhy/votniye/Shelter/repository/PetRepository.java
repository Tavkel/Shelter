package zhy.votniye.Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhy.votniye.Shelter.models.AdoptionalRequest;

@Repository
public interface PetRepository extends JpaRepository<AdoptionalRequest, Long> {
}
