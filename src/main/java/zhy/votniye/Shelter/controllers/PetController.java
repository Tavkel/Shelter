package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class PetController {

    public final PetService petService;

    private final int maxFileSizeInKb = 300;

    public PetController(PetService petService){
        this.petService=petService;
    }

    @PostMapping
    public Pet create(@RequestBody Pet pet) {
        return petService.create(pet);
    }

    @GetMapping("/{id}")
    public Pet read(@PathVariable long id){
        return petService.reade(id);
    }

    @PutMapping
    public Pet update(@RequestBody Pet pet){
        return petService.update(pet);
    }

    @DeleteMapping("/{id}")
    public Pet delete(@PathVariable long id){
        return petService.delete(id);
    }


}
