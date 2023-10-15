package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.mapper.AdoptionRequestMapper;
import zhy.votniye.Shelter.models.DTO.AdoptionRequestDTO;
import zhy.votniye.Shelter.services.interfaces.AdoptionRequestService;

import static zhy.votniye.Shelter.mapper.AdoptionRequestMapper.*;

@RestController
@RequestMapping("/adoptionRequest")
public class AdoptionRequestController {

    public final AdoptionRequestService adoptionRequestService;

    public AdoptionRequestController(AdoptionRequestService adoptionRequestService) {
        this.adoptionRequestService = adoptionRequestService;
    }

    @PostMapping
    public AdoptionRequestDTO create(@RequestBody AdoptionRequestDTO adoptionRequestDTO) {

        var adoptionRequest = toAdoptionRequest(adoptionRequestDTO);

        return fromAdoptionRequest(adoptionRequestService.create(adoptionRequest));
    }

    @GetMapping("/{id}")
    public AdoptionRequestDTO read(@PathVariable long id) {

        return fromAdoptionRequest(adoptionRequestService.read(id));
    }

    @PutMapping
    public AdoptionRequestDTO update(AdoptionRequestDTO adoptionRequestDTO) {

        var adoptionRequest = toAdoptionRequest(adoptionRequestDTO);

        return fromAdoptionRequest(adoptionRequestService.update(adoptionRequest));
    }

    @DeleteMapping("/{id}")
    public AdoptionRequestDTO delete(@PathVariable long id) {
        return fromAdoptionRequest(adoptionRequestService.delete(id));
    }
}
