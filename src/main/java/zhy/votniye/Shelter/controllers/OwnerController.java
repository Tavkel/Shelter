package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.controllers.interfaces.IOwnerController;
import zhy.votniye.Shelter.utils.mappers.OwnerMapper;
import zhy.votniye.Shelter.models.DTO.OwnerDTO;
import zhy.votniye.Shelter.services.interfaces.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController implements IOwnerController {

    public final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    @Override
    public OwnerDTO create(@RequestBody OwnerDTO ownerDTO) {
        var owner = OwnerMapper.toOwner(ownerDTO);
        return OwnerMapper.fromOwner(ownerService.create(owner));
    }

    @GetMapping("/{ownerId}")
    @Override
    public OwnerDTO read(@PathVariable long ownerId) {
        return OwnerMapper.fromOwner(ownerService.read(ownerId));
    }

    @PutMapping
    @Override
    public OwnerDTO update(@RequestBody OwnerDTO ownerDTO) {
        var owner = OwnerMapper.toOwner(ownerDTO);
        return OwnerMapper.fromOwner(ownerService.update(owner));
    }

    @DeleteMapping("/{ownerId}")
    @Override
    public OwnerDTO delete(@PathVariable long ownerId) {
        return OwnerMapper.fromOwner(ownerService.delete(ownerId));
    }
}
