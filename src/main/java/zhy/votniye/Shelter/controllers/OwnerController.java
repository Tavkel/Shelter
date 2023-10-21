package zhy.votniye.Shelter.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.models.DTO.OwnerDTO;
import zhy.votniye.Shelter.services.interfaces.OwnerService;

import static zhy.votniye.Shelter.mapper.OwnerMapper.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    public final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

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
    public OwnerDTO create(@RequestBody OwnerDTO ownerDTO) {
        var owner = toOwner(ownerDTO);
        return fromOwner(ownerService.create(owner));
    }

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
    public OwnerDTO read(@PathVariable long ownerId) {
        return fromOwner(ownerService.read(ownerId));
    }

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
    public OwnerDTO update(OwnerDTO ownerDTO) {
        var owner = toOwner(ownerDTO);
        return fromOwner(ownerService.update(owner));
    }

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
    public OwnerDTO delete(@PathVariable long ownerId) {
        return fromOwner(ownerService.delete(ownerId));
    }
}
