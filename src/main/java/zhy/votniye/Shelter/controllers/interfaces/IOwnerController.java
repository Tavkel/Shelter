package zhy.votniye.Shelter.controllers.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.models.DTO.OwnerDTO;

public interface IOwnerController {
    @Operation(summary = "create owner", tags = "Owners")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "created owner",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OwnerDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Owner exists",
                    content = @Content(mediaType = MediaType.TEXT_EVENT_STREAM_VALUE)
            )
    })
    @PostMapping
    OwnerDTO create(@RequestBody OwnerDTO ownerDTO);

    @Operation(summary = "find owner", tags = "Owners")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "find owner",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OwnerDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "owner not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @GetMapping("/{ownerId}")
    OwnerDTO read(@PathVariable long ownerId);

    @Operation(summary = "update owner", tags = "Owners")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "updated owner",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OwnerDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "owner not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @PutMapping
    OwnerDTO update(@RequestBody OwnerDTO ownerDTO);

    @Operation(summary = "delete owner", tags = "Owners")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "deleted owner",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OwnerDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Owner not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @DeleteMapping("/{ownerId}")
    OwnerDTO delete(@PathVariable long ownerId);
}
