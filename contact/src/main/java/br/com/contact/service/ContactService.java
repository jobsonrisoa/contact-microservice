package br.com.contact.service;

import br.com.contact.model.Contact;

import java.util.List;

public interface ContactService {

    void createContact(Contact contact);
    void updateContact(Contact contact);
    void removeContact(Long id);
    List<Contact> getAllContacts();
    Contact getContactByName(String name);


}
