package zhy.votniye.Shelter.service.interfaces;

import zhy.votniye.Shelter.models.AdoptionRequest;

import java.util.List;

public interface AdoptionRequestService {
    AdoptionRequest create(AdoptionRequest adoptionRequest);

    AdoptionRequest read(Long id);

    AdoptionRequest update(AdoptionRequest adoption);

    AdoptionRequest delete(Long id);

    List<AdoptionRequest> readAll();
}
