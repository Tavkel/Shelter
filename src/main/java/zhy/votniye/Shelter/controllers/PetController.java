package zhy.votniye.Shelter.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zhy.votniye.Shelter.models.Pet;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/pet")
public class PetController {

    public final PetService petService;

    private final int maxFileSizeInKb = 300;

    public PetController(PetService petService){
        this.petService=petService;
    }

    @PostMapping
    public Pet create(@RequestBody Pet pet) {
        return petService.create(pet);
    }

    @GetMapping("/{id}")
    public Pet read(@PathVariable long id){
        return petService.read(id);
    }

    @PutMapping
    public Pet update(@RequestBody Pet pet){
        return petService.update(pet);
    }

    @DeleteMapping("/{id}")
    public Pet delete(@PathVariable long id){
        return petService.delete(id);
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
