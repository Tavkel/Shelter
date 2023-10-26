package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhy.votniye.Shelter.models.DTO.CatDTO;
import zhy.votniye.Shelter.models.domain.Cat;
import zhy.votniye.Shelter.services.interfaces.PetService;
import zhy.votniye.Shelter.utils.mappers.PetMapper;

@RestController
@RequestMapping("/cat")
public class CatController extends PetController<CatDTO> {
    public CatController(PetService<Cat> petService, PetMapper<Cat, CatDTO> petMapper) {
        super(petService, petMapper);
    }
}
