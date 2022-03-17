package br.com.contact.service;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.repository.ContactRepository;
import br.com.contact.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void createContact(ContactRequest request) {

        this.contactRepository.save(new Contact().convertRequestToContact(request));

    }

    @Override
    public void updateContact(ContactRequest request) {
        this.contactRepository.save(new Contact().convertRequestToContact(request));
    }

    @Override
    public void updateContactById(ContactRequest request, Long id) {
        List<Contact> contacts= this.contactRepository.findAll()
                .stream()
                .collect(Collectors.toList());

        Contact contactById = contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findAny()
                .orElse(null);

        contactById.setName(request.getName());
        contactById.setEmail(request.getEmail());
        contactById.setPhone(request.getPhone());

        this.contactRepository.save(contactById);

    }

    @Override
    public void removeContact(Long id) {
        this.contactRepository.deleteById(id);
    }

    @Override
    public ContactResponse getContactByName(String name) {
        List<ContactResponse> contactsResponse = this.contactRepository.findAll()
                .stream().map(contact -> contact.convertContactToResponse(contact))
                .collect(Collectors.toList());

        return  contactsResponse.stream()
                .filter(contact -> contact.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<ContactResponse> getAllContacts() {
        return this.contactRepository.findAll()
                .stream().map(contact -> contact.convertContactToResponse(contact))
                .collect(Collectors.toList());
    }

}
