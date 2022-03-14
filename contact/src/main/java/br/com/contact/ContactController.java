package br.com.contact;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/V1/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    private final List<Contact> contacts = new ArrayList<>();
    private static Long nextId = 1L;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Contact> get() {
        return this.contacts;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createContact(@RequestBody Contact contact) {
        contact.setId(nextId);
        this.contacts.add(contact);
        nextId++;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteContactByPathVariable(@PathVariable("id") Long id) {
        Contact deleteContact = this.contacts.stream()
                .filter(c -> Objects.equals(c.getId(), id))
                .findAny()
                .orElse(null);
        this.contacts.remove(deleteContact);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteContactByRequestParam(@RequestParam Long id) {
        this.contacts.removeIf(contact -> contact.getId().equals(id));
    }

    @GetMapping("/find-by-name")
    @ResponseStatus(HttpStatus.OK)
    public Contact getContactByName(@RequestParam String name) {
        return this.contacts.stream()
                .filter(contact -> contact.getName().equals(name)).findAny().orElse(null);
    }

}
