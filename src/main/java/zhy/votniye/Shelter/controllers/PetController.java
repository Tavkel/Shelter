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
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.domain.Pet;
import zhy.votniye.Shelter.services.interfaces.PetService;
import zhy.votniye.Shelter.utils.mappers.PetMapper;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public abstract class PetController<T extends PetDTO> {

    private final PetService petService;
    private final PetMapper petMapper;

    public PetController(PetService petService, PetMapper petMapper) {
        this.petService = petService;
        this.petMapper = petMapper;
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
    public T create(@Parameter(description = "object PetDTO", example = "test") @RequestBody T petDTO) {
        var pet = petMapper.toPet(petDTO);

        return (T) petMapper.fromPet(petService.create(pet));
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
    public T read(@PathVariable long petId) {
        return (T) petMapper.fromPet(petService.read(petId));
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
    public T update(@RequestBody T petDTO) {
        var pet = petMapper.toPet(petDTO);

        return (T) petMapper.fromPet(petService.update(pet));
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
    public T delete(@PathVariable long petId) {
        return (T) petMapper.fromPet(petService.delete(petId));
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
        List<Pet> tmp = petService.readAll();
        return tmp.stream().map(p -> petMapper.fromPet(p)).toList();
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
    //not implemented!
    @GetMapping(value = "/all")
    public Collection<PetDTO> getPetPage(@RequestParam int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        }
        List<Pet> tmp = petService.readAllPagination(pageNum);
        return tmp.stream().map(p -> petMapper.fromPet(p)).toList();
    }

    @Operation(summary = "upload pet photo", tags = "Pets")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "pet photo uploaded",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
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

