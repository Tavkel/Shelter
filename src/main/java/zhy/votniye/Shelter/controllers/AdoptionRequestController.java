package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.utils.mappers.AdoptionRequestMapper;
import zhy.votniye.Shelter.models.DTO.AdoptionRequestDTO;
import zhy.votniye.Shelter.models.domain.AdoptionRequest;
import zhy.votniye.Shelter.services.interfaces.AdoptionRequestService;

import java.util.List;

@RestController
@RequestMapping("/adoptionRequest")
public class AdoptionRequestController {

    public final AdoptionRequestService adoptionRequestService;

    public AdoptionRequestController(AdoptionRequestService adoptionRequestService) {
        this.adoptionRequestService = adoptionRequestService;
    }

    @PostMapping
    public AdoptionRequestDTO create(@RequestBody AdoptionRequestDTO adoptionRequestDTO) {

        var adoptionRequest = AdoptionRequestMapper.toAdoptionRequest(adoptionRequestDTO);

        return AdoptionRequestMapper.fromAdoptionRequest(adoptionRequestService.create(adoptionRequest));
    }

    @GetMapping("/{id}")
    public AdoptionRequestDTO read(@PathVariable long id) {

        return AdoptionRequestMapper.fromAdoptionRequest(adoptionRequestService.read(id));
    }

    @PutMapping
    public AdoptionRequestDTO update(AdoptionRequestDTO adoptionRequestDTO) {

        var adoptionRequest = AdoptionRequestMapper.toAdoptionRequest(adoptionRequestDTO);

        return AdoptionRequestMapper.fromAdoptionRequest(adoptionRequestService.update(adoptionRequest));
    }

    @DeleteMapping("/{id}")
    public AdoptionRequestDTO delete(@PathVariable long id) {
        return AdoptionRequestMapper.fromAdoptionRequest(adoptionRequestService.delete(id));
    }

    //todo change to dto
    @GetMapping
    public List<AdoptionRequest> readAll() {
        return adoptionRequestService.readAll();
    }
}
