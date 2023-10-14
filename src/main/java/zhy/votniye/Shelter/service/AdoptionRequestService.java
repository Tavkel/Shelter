package zhy.votniye.Shelter.service;

import zhy.votniye.Shelter.models.AdoptionRequest;

import java.util.List;

public interface AdoptionRequestService {
    AdoptionRequest create(AdoptionRequest adoptionRequest);

    AdoptionRequest read(Long id);

    AdoptionRequest update(Long id);

    AdoptionRequest delete(Long id);

    List<AdoptionRequest> readAll();
}
