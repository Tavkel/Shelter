package zhy.votniye.Shelter.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.utils.mappers.ContactMapper;
import zhy.votniye.Shelter.models.DTO.ContactDTO;
import zhy.votniye.Shelter.services.interfaces.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {

    public final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @Operation(summary = "create contact", tags = "Owners")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "created contact for owner",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContactDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Owner not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @PostMapping
    public ContactDTO create(@RequestBody ContactDTO contactDTO) {

        var contact = ContactMapper.toContact(contactDTO);

        return ContactMapper.fromContact(contactService.create(contact));
    }

    @Operation(summary = "find owner contact", tags = "Owners")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "find owners contact",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContactDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Owner contact not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @GetMapping("/{contactId}")
    public ContactDTO read(@PathVariable long contactId) {
        return ContactMapper.fromContact(contactService.read(contactId));
    }

    @Operation(summary = "update owner contact", tags = "Owners")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "updated owner contact",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ContactDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Owner contact not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @PutMapping
    public ContactDTO update(@RequestBody ContactDTO contactDTO) {

        var contact = ContactMapper.toContact(contactDTO);

        return ContactMapper.fromContact(contactService.update(contact));
    }

//    @Operation(summary = "delete owner contact", tags = "Owners")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200",
//                    description = "delete owner contact",
//                    content = @Content(
//                            mediaType = MediaType.APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = ContactDTO.class)
//                    )
//            ),
//            @ApiResponse(
//                    responseCode = "404",
//                    description = "Owner contact not found",
//                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
//            )
//    })
//    @DeleteMapping("/{contactId}")
//    public ContactDTO delete(@PathVariable long contactId){
//        return fromContact(contactService.delete(contactId));
//    }
}
