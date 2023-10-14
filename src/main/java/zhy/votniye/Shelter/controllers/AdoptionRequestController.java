package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.mapper.AdoptionRequestMapper;
import zhy.votniye.Shelter.models.DTO.AdoptionRequestDTO;

@RestController
@RequestMapping("/adoptionRequest")
public class AdoptionRequestController {

    public final AdoptionRequestService adoptionRequestService;
    public final AdoptionRequestMapper adoptionRequestMapper;




    public AdoptionRequestController(AdoptionRequestService adoptionRequestService,
                                     AdoptionRequestMapper adoptionRequestMapper){
        this.adoptionRequestService=adoptionRequestService;
        this.adoptionRequestMapper=adoptionRequestMapper;
    }

    @PostMapping
    public AdoptionRequestDTO create(@RequestBody AdoptionRequestDTO adoptionRequestDTO){

        var adoptionRequest = adoptionRequestMapper.toAdoptionRequest(adoptionRequestDTO);

        return adoptionRequestMapper.fromAdoptionRequest(adoptionRequestService.create(adoptionRequest));
    }

    @GetMapping("/{id}")
    public AdoptionRequestDTO read(@PathVariable long id){

        return adoptionRequestMapper.fromAdoptionRequest(adoptionRequestService.read(id));
    }

    @PutMapping
    public AdoptionRequestDTO update(AdoptionRequestDTO adoptionRequestDTO){

        var adoptionRequest = adoptionRequestMapper.toAdoptionRequest(adoptionRequestDTO);

        return adoptionRequestMapper.fromAdoptionRequest(adoptionRequestService.update(adoptionRequest));
    }

    @DeleteMapping("/{id}")
    public AdoptionRequestDTO delete(@PathVariable long id){
        return adoptionRequestMapper.fromAdoptionRequest(adoptionRequestService.delete(id));
    }


}
