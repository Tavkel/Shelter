package zhy.votniye.Shelter.controllers.interfaces;

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

import java.io.IOException;
import java.util.Collection;

public interface IPetController<T extends PetDTO> {
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
    T create(@Parameter(description = "object PetDTO", example = "test") @RequestBody PetDTO petDTO);

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
    PetDTO read(@PathVariable long petId);

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
    PetDTO update(@RequestBody PetDTO petDTO);

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
    PetDTO delete(@PathVariable long petId);

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
    Collection<PetDTO> readAll();

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
    Collection<PetDTO> getPetPage(@RequestParam int pageNum);

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
    String uploadPetPhoto(@PathVariable long petId, @RequestParam MultipartFile file)
            throws IOException;
}
