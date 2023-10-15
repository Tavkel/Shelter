package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.models.DTO.OwnerDTO;
import zhy.votniye.Shelter.services.interfaces.OwnerService;

import static zhy.votniye.Shelter.mapper.OwnerMapper.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    public final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService=ownerService;
    }

    @PostMapping
    public OwnerDTO create(@RequestBody OwnerDTO ownerDTO) {
        var owner = toOwner(ownerDTO);
        return fromOwner(ownerService.create(owner));
    }

    @GetMapping("/{ownerId}")
    public OwnerDTO read(@PathVariable long ownerId){
        return fromOwner(ownerService.read(ownerId));
    }

    @PutMapping
    public OwnerDTO update(OwnerDTO ownerDTO){
        var owner = toOwner(ownerDTO);
        return fromOwner(ownerService.update(owner));
    }

    @DeleteMapping("/{ownerId}")
    public OwnerDTO delete(@PathVariable long ownerId){
        return fromOwner(ownerService.delete(ownerId));
    }
}
