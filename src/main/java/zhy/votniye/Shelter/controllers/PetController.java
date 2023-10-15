package zhy.votniye.Shelter.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.domain.Pet;
import zhy.votniye.Shelter.services.interfaces.PetService;

import java.io.IOException;
import java.util.Collection;

import static zhy.votniye.Shelter.mapper.PetMapper.*;

@RestController
@RequestMapping("/pet")
public class PetController {

    public final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public PetDTO create(@RequestBody PetDTO petDTO) {
        var pet = toPet(petDTO);

        return fromPet(petService.create(pet));
    }

    @GetMapping("/{petId}")
    public PetDTO read(@PathVariable long petId) {
        return fromPet(petService.read(petId));
    }

    @PutMapping
    public PetDTO update(@RequestBody PetDTO petDTO) {
        var pet = toPet(petDTO);

        return fromPet(petService.update(pet));
    }

    @DeleteMapping("/{petId}")
    public PetDTO delete(@PathVariable long petId) {
        return fromPet(petService.delete(petId));
    }

    @GetMapping
    public Collection<Pet> readAll() {
        return petService.readAll();
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<Pet>> getPetPage(@RequestParam int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        }

        return new ResponseEntity<>(petService.readAllPagination(pageNum), HttpStatus.OK);
    }

//    @PostMapping(value = "/{petId}",
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> uploadPhoto(@PathVariable Long petId, @RequestParam MultipartFile photo)
//            throws IOException {
//
//        if (photo.getSize() > 1024 * maxFileSizeInKb) {
//            return ResponseEntity.badRequest().body("picture size is big");
//        }
//        petService.uploadPhoto(petId, photo);
//        return ResponseEntity.ok().body("picture was saved");
//    }
}

