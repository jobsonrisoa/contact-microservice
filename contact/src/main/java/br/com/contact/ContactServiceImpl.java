package br.com.contact;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void createContact(Contact contact) {
        this.contactRepository.save(contact);
    }

    @Override
    public void removeContact(Long id) {
        this.contactRepository.deleteById(id);
    }

    @Override
    public Contact getContactByName(String name) {
        List<Contact> contacts = this.contactRepository.findAll();
        return  contacts.stream()
                .filter(contact -> contact.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    @Override
    public Contact getByName(String name) {
        return this.contactRepository.findByName(name);
    }

    @Override
    public List<Contact> getAllContacts(String name) {
       return this.contactRepository.findAll();
    }
}
