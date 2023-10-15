package zhy.votniye.Shelter.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zhy.votniye.Shelter.mapper.PetMapper;
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.Pet;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/pet")
public class PetController {

    public final PetService petService;

    public final PetMapper petMapper;

    public PetController(PetService petService, PetMapper petMapper ){

        this.petService=petService;
        this.petMapper=petMapper;
    }

    @PostMapping
    public PetDTO create(@RequestBody PetDTO petDTO) {

        var pet = petMapper.toPet(petDTO);

        return petMapper.fromPet(petService.create(pet));
    }

    @GetMapping("/{id}")
    public PetDTO read(@PathVariable long petId){

        return petMapper.fromPet(petService.read(petId));
    }

    @PutMapping
    public PetDTO update(@RequestBody PetDTO petDTO) {

        var pet = petMapper.toPet(petDTO);

        return petMapper.fromPet(petService.update(pet));

    }

    @DeleteMapping("/{id}")
    public PetDTO delete(@PathVariable long petId){

        return petMapper.fromPet(petService.delete(petId));
    }

    @GetMapping
    public Collection<Pet> readAll(){
        return petService.readAll();
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<Pet>> getPetPage(@RequestParam int pageNum) {
        if(pageNum < 1) {
            pageNum = 1;
        }

        return new ResponseEntity<>(petService.getPetPaged(pageNum));
    }

    @PostMapping(value = "/{petId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPhoto(@PathVariable Long petId, @RequestParam MultipartFile photo)
            throws IOException {

        if (photo.getSize() > 1024 * maxFileSizeInKb) {
            return ResponseEntity.badRequest().body("picture size is big");
        }
        petService.uploadPhoto(petId, photo);
        return ResponseEntity.ok().body("picture is save");
    }

}
