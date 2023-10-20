package zhy.votniye.Shelter.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.Extensions;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "create pet",
            responses ={


            @ApiResponse(
            responseCode = "200",
            description = "created pets",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = Pet.class))
                    )
        )
    },
            tags = "Pets"

    )
    @PostMapping
    public PetDTO create(@Parameter(description = "object PetDTO",example = "test") @RequestBody PetDTO petDTO) {
        var pet = toPet(petDTO);

        var res = fromPet(petService.create(pet));

        return res;
    }

    @GetMapping("/{petId}")
    public PetDTO read(@PathVariable long petId) {
        return fromPet(petService.read(petId));
    }

    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "updating pet",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pet.class)
                    )
            )
    )
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
    @Operation(summary = "view 5 photo of pets",
            responses ={


                    @ApiResponse(
                            responseCode = "200",
                            description = "view pets photo",
                            content = @Content(
                                    mediaType = MediaType.IMAGE_JPEG_VALUE

                            )
                    )
            },
            tags = "Pets photo"

    )
    @GetMapping(value = "/all")
    public ResponseEntity<Collection<Pet>> getPetPage(@RequestParam int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        }

        return new ResponseEntity<>(petService.readAllPagination(pageNum), HttpStatus.OK);
    }
    @Operation(summary = "upload pets photo",
            responses ={


                    @ApiResponse(
                            responseCode = "200",
                            description = "uploaded pet photo",
                            content = @Content(
                                    mediaType = MediaType.IMAGE_JPEG_VALUE

                            )
                    )
            },
            tags = "Pets photo"

    )
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

