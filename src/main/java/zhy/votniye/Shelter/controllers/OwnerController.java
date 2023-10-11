package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class OwnerController {

    public final OwnerService ownerService;

    public OwnerController(OwnerService ownerService){
        this.ownerService=ownerService;
    }

    @PostMapping
    public Owner create(@RequestBody Owner owner){
        return ownerService.create(owner);
    }

    @GetMapping("/{id}")
    public Owner read(@PathVariable long id){
        return ownerService.read(id);
    }

    @PutMapping
    public Owner update(Owner owner){
        return ownerService.update(owner);
    }

    @DeleteMapping("/{id}")
    public Owner delete(@PathVariable long id){
        return ownerService.delete(id);
    }
}
