package br.com.contact.controller;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.service.ContactService;
import br.com.contact.model.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/V1/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContactResponse> getAllContacts() {
        return this.contactService.getAllContacts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createContact(@RequestBody ContactRequest request) {
        this.contactService.createContact(request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void removeContact(@RequestParam Long id) {
        this.contactService.removeContact(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateContact(@RequestBody ContactRequest request) {
        this.contactService.updateContact(request);
    }

    @PutMapping("/update-by-id")
    @ResponseStatus(HttpStatus.OK)
    public void updateContactById(@RequestBody ContactRequest request, @RequestParam Long id) {
        this.contactService.updateContactById(request, id);
    }

    @GetMapping("/find-by-name")
    @ResponseStatus(HttpStatus.OK)
    public ContactResponse getContactByName(@RequestParam String name) {
        return this.contactService.getContactByName(name);

    }

}
