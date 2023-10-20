package zhy.votniye.Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhy.votniye.Shelter.models.domain.Pet;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> findByNameAndBreedAndWeight(
            String name,String breed, float weight);

}
