package br.com.contact.service;

import br.com.contact.controller.request.ContactRequest;
import br.com.contact.controller.response.ContactResponse;
import br.com.contact.model.Contact;

import java.util.List;

public interface ContactService {

    void createContact(ContactRequest request);
    void updateContact(ContactRequest request);
    void removeContact(Long id);
    List<ContactResponse> getAllContacts();
    ContactResponse getContactByName(String name);


}
