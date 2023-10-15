package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.models.DTO.ContactDTO;
import zhy.votniye.Shelter.services.interfaces.ContactService;

import static zhy.votniye.Shelter.mapper.ContactMapper.*;
@RestController
@RequestMapping("/contact")
public class ContactController {

    public final ContactService contactService;

    public ContactController(ContactService contactService){
        this.contactService=contactService;
    }

    @PostMapping
    public ContactDTO create(@RequestBody ContactDTO contactDTO) {

        var contact = toContact(contactDTO);

        return fromContact(contactService.create(contact));
    }

    @GetMapping("/{id}")
    public ContactDTO read(@PathVariable long contactId){
        return fromContact(contactService.read(contactId));
    }

    @PutMapping
    public ContactDTO update(@RequestBody ContactDTO contactDTO){

        var contact = toContact(contactDTO);

        return fromContact(contactService.update(contact));
    }

    @DeleteMapping("/{id}")
    public ContactDTO delete(@PathVariable long contactId){
        return fromContact(contactService.delete(contactId));
    }
}
