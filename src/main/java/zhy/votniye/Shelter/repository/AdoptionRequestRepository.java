package zhy.votniye.Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhy.votniye.Shelter.models.domain.AdoptionRequest;

public interface AdoptionRequestRepository extends JpaRepository <AdoptionRequest,Long> {
}
