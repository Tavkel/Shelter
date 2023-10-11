package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adoptionRequest")
public class AdoptionRequestController {

    public final AdoptionRequestService adoptionRequestService;

    private final int maxFileSizeInKb = 300;

    public AdoptionRequestController(AdoptionRequestService adoptionRequestService){
        this.adoptionRequestService=adoptionRequestService;
    }

    @PostMapping
    public AdoptionRequest create(@RequestBody AdoptionRequest adoptionRequest){
        return adoptionRequestService.create(adoptionRequest);
    }

    @GetMapping("/{id}")
    public AdoptionRequest read(@PathVariable long id){
        return adoptionRequestService.read(id);
    }

    @PutMapping
    public AdoptionRequest update(AdoptionRequest adoptionRequest){
        return adoptionRequestService.update(adoptionRequest);
    }

    @DeleteMapping("/{id}")
    public AdoptionRequest delete(@PathVariable long id){
        return adoptionRequestService.delete(id);
    }


}
