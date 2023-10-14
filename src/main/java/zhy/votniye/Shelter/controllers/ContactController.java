package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.mapper.ContactMapper;
import zhy.votniye.Shelter.models.DTO.ContactDTO;

@RestController
@RequestMapping("/contact")
public class ContactController {

    public final ContactService contactService;
    public final ContactMapper contactMapper;

    public ContactController(ContactService contactService, ContactMapper contactMapper){
        this.contactService=contactService;
        this.contactMapper=contactMapper;

    }

    @PostMapping
    public ContactDTO create(@RequestBody ContactDTO contactDTO) {

        var contact = contactMapper.toContact(contactDTO);

        return contactMapper.fromContact(contactService.create(contact));
    }

    @GetMapping("/{id}")
    public ContactDTO read(@PathVariable long contactId){
        return contactMapper.fromContact(contactService.read(contactId));
    }

    @PutMapping
    public ContactDTO update(@RequestBody ContactDTO contactDTO){

        var contact = contactMapper.toContact(contactDTO);

        return contactMapper.fromContact(contactService.update(contact));
    }

    @DeleteMapping("/{id}")
    public ContactDTO delete(@PathVariable long contactId){
        return contactMapper.fromContact(contactService.delete(contactId));
    }
}
