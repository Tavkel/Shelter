package zhy.votniye.Shelter.services.interfaces;

import zhy.votniye.Shelter.models.domain.AdoptionRequest;

import java.util.List;

public interface AdoptionRequestService {
    AdoptionRequest create(AdoptionRequest adoptionRequest);

    AdoptionRequest read(Long id);

    AdoptionRequest update(AdoptionRequest adoption);

    AdoptionRequest delete(Long id);

    List<AdoptionRequest> readAll();
}
