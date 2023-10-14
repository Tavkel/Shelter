package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.mapper.OwnerMapper;
import zhy.votniye.Shelter.mapper.PetMapper;
import zhy.votniye.Shelter.models.DTO.OwnerDTO;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    public final OwnerService ownerService;

    public final OwnerMapper ownerMapper;

    public OwnerController(OwnerService ownerService, OwnerMapper ownerMapper) {
        this.ownerService=ownerService;
        this.ownerMapper=ownerMapper;
    }

    @PostMapping
    public OwnerDTO create(@RequestBody OwnerDTO ownerDTO) {

        var owner = ownerMapper.toOwner(ownerDTO);

        return ownerMapper.fromOwner(ownerService.create(owner));
    }

    @GetMapping("/{id}")
    public OwnerDTO read(@PathVariable long ownerId){

        return ownerMapper.fromOwner(ownerService.read(ownerId));
    }

    @PutMapping
    public OwnerDTO update(OwnerDTO ownerDTO){

        var owner = ownerMapper.toOwner(ownerDTO);

        return ownerMapper.fromOwner(ownerService.update(owner));
    }

    @DeleteMapping("/{id}")
    public OwnerDTO delete(@PathVariable long ownerId){

        return ownerMapper.fromOwner(ownerService.delete(ownerId));
    }
}
