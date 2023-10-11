package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    public final ContactService contactService;

    public ContactController(ContactService contactService){
        this.contactService=contactService;
    }

    @PostMapping
    public Contact create(@RequestBody Contact contact){
        return contactService.create(contact);
    }

    @GetMapping("/{id}")
    public Contact read(@PathVariable long id){
        return contactService.read(id);
    }

    @PutMapping
    public Contact update(@RequestBody Contact contact){
        return contactService.update(contact);
    }

    @DeleteMapping("/{id}")
    public Contact delete(@PathVariable long id){
        return contactService.delete(id);
    }
}
