package zhy.votniye.Shelter.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zhy.votniye.Shelter.utils.mappers.PetMapper;
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.services.interfaces.PetService;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/pet")
public class PetController {
    public final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }


    @Operation(summary = "create pet", tags = "Pets")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "created pets",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PetDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Pet exists",
                    content = @Content(mediaType = MediaType.TEXT_EVENT_STREAM_VALUE)
            )
    })
    @PostMapping
    public PetDTO create(@Parameter(description = "object PetDTO", example = "test") @RequestBody PetDTO petDTO) {
        var pet = PetMapper.toPet(petDTO);

        return PetMapper.fromPet(petService.create(pet));
    }

    @Operation(summary = "found pet", tags = "Pets")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "found pet",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PetDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pet not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @GetMapping("/{petId}")
    public PetDTO read(@PathVariable long petId) {
        return PetMapper.fromPet(petService.read(petId));
    }

    @Operation(summary = "update pet", tags = "Pets")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "updated pet",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PetDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pet not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @PutMapping
    public PetDTO update(@RequestBody PetDTO petDTO) {
        var pet = PetMapper.toPet(petDTO);

        return PetMapper.fromPet(petService.update(pet));
    }

    @Operation(summary = "delete pet", tags = "Pets")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "deleted pet",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PetDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pet not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @DeleteMapping("/{petId}")
    public PetDTO delete(@PathVariable long petId) {
        return PetMapper.fromPet(petService.delete(petId));
    }

    @Operation(summary = "find all pets", tags = "Pets")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "find all pets",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PetDTO.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pets not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @GetMapping
    public Collection<PetDTO> readAll() {
        return petService.readAll().stream().map(PetMapper::fromPet).toList();
    }

    @Operation(summary = "view five pets", tags = "Pets")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "find five pets",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PetDTO.class)
                    )
            )

    })
    @GetMapping(value = "/all")
    public Collection<PetDTO> getPetPage(@RequestParam int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        }

        return petService.readAllPagination(pageNum).stream().map(PetMapper::fromPet).toList();
    }

    @Operation(summary = "upload pet photo", tags = "Pets")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "pet photo uploaded",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PetDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pets not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "error saving photo",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )

    })
    @PostMapping(value = "/{petId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadPetPhoto(@PathVariable long petId, @RequestParam MultipartFile file)
            throws IOException {
        petService.savePetPhoto(petId, file);
        return "File saved";
    }
}

