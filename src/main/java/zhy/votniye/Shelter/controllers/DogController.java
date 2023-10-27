package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhy.votniye.Shelter.models.DTO.DogDTO;
import zhy.votniye.Shelter.models.domain.Dog;
import zhy.votniye.Shelter.services.interfaces.PetService;
import zhy.votniye.Shelter.utils.mappers.PetMapper;

@RestController
@RequestMapping("/dog")
public class DogController extends PetController<DogDTO>{
    public DogController(PetService<Dog> petService,
                         PetMapper<Dog, DogDTO> petMapper) {
        super(petService, petMapper);
    }
}
