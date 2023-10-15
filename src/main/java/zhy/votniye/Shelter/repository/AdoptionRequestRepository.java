package zhy.votniye.Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhy.votniye.Shelter.models.AdoptionRequest;

public interface AdoptionRequestRepository extends JpaRepository <AdoptionRequest,Long> {
}
