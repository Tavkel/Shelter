package zhy.votniye.Shelter.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zhy.votniye.Shelter.controllers.interfaces.IPetController;
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.domain.Pet;
import zhy.votniye.Shelter.services.interfaces.PetService;
import zhy.votniye.Shelter.utils.mappers.PetMapper;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public abstract class PetController<T extends PetDTO> implements IPetController<T> {

    private final PetService petService;
    private final PetMapper petMapper;

    public PetController(PetService petService, PetMapper petMapper) {
        this.petService = petService;
        this.petMapper = petMapper;
    }

    @PostMapping
    @Override
    public T create(@Parameter(description = "object PetDTO", example = "test") @RequestBody T petDTO) {
        var pet = petMapper.toPet(petDTO);

        return (T) petMapper.fromPet(petService.create(pet));
    }

    @GetMapping("/{petId}")
    @Override
    public T read(@PathVariable long petId) {
        return (T) petMapper.fromPet(petService.read(petId));
    }

    @PutMapping
    @Override
    public T update(@RequestBody PetDTO petDTO) {
        var pet = petMapper.toPet(petDTO);

        return (T) petMapper.fromPet(petService.update(pet));
    }

    @DeleteMapping("/{petId}")
    @Override
    public T delete(@PathVariable long petId) {
        return (T) petMapper.fromPet(petService.delete(petId));
    }

    @GetMapping
    @Override
    public Collection<PetDTO> readAll() {
        List<Pet> tmp = petService.readAll();
        return tmp.stream().map(p -> petMapper.fromPet(p)).toList();
    }

    //todo implement!
    @GetMapping(value = "/all")
    @Override
    public Collection<PetDTO> getPetPage(@RequestParam int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        }
        List<Pet> tmp = petService.readAllPagination(pageNum);
        return tmp.stream().map(p -> petMapper.fromPet(p)).toList();
    }

    @PostMapping(value = "/{petId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Override
    public String uploadPetPhoto(@PathVariable long petId, @RequestParam MultipartFile file)
            throws IOException {
        petService.savePetPhoto(petId, file);
        return "File saved";
    }
}