package zhy.votniye.Shelter.controllers;

import org.springframework.beans.factory.annotation.Value;
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
//    @Value("${upload-file-size-limit}")
//    private int fileSizeLimit;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public PetDTO create(@RequestBody PetDTO petDTO) {
        var pet = toPet(petDTO);

        var res = fromPet(petService.create(pet));

        return res;
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

    @PostMapping(value = "/{petId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPetPhoto(@PathVariable long petId,
                                               @RequestParam MultipartFile file) throws IOException {
//        if (file.getSize() > fileSizeLimit * 1024L) {
//            return new ResponseEntity<>("File too big.", HttpStatus.BAD_REQUEST);
//        }

        petService.savePetPhoto(petId, file);
        return new ResponseEntity<>("File saved", HttpStatus.OK);
    }
}

