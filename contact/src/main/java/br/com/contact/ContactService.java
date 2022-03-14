package br.com.contact;

import java.util.List;

public interface ContactService {

    public void createContact(Contact contact);
    public void removeContact(Long id);
    public Contact getContactByName(String name);
    public Contact getByName(String name);
    public List<Contact> getAllContacts(String name);
}
