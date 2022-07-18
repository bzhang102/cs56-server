package cs56.group2.contact.server.controller;

import cs56.group2.contact.server.model.Contact;
import cs56.group2.contact.server.repository.ContactRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactServerController {

    private static Logger LOGGER = LoggerFactory.getLogger(ContactServerController.class);

    @Autowired
    ContactRepository contactRepository;

    @PostMapping(value = "/contact")
    public void createOrUpdate(@RequestBody List<Contact> contacts) {
        LOGGER.info("createOrUpdate {} contacts", contacts.size());

        contactRepository.deleteAll();
        
        for (Contact contact : contacts) {
            LOGGER.info("createOrUpdate contact {}", contact);
            contactRepository.save(contact);
            LOGGER.info("successfully saved contact {}", contact);
        }

        LOGGER.info("successfully saved {} contacts", contacts.size());
    }


    @GetMapping(value = "/contact")
    public List<Contact> getAllContacts() {
        LOGGER.info("find all contacts from db");
        List<Contact> contacts = contactRepository.findAll();
        LOGGER.info("number of contacts found fromm db: {}", contacts.size());

        return contacts;
    }

    @DeleteMapping(value = "/contact/{id}")
    public void deleteContact(@PathVariable Integer id) {
        LOGGER.info("deleting contact, id = {}", id);

        Contact contact = contactRepository.findById(id).get();

        if (contact == null) {
            LOGGER.warn("contact {} not found, skip deleting", id);

            return;
        }

        contactRepository.deleteById(id);
        LOGGER.info("successfully deleted contact {}", contact);
    }
}
