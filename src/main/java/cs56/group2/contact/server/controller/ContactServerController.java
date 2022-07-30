package cs56.group2.contact.server.controller;

import cs56.group2.contact.server.model.SimpleContact;
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
    public void createOrUpdate(@RequestBody List<SimpleContact> simpleContacts) {
        LOGGER.info("createOrUpdate {} contacts", simpleContacts.size());

        contactRepository.deleteAll();
        
        for (SimpleContact simpleContact : simpleContacts) {
            LOGGER.info("createOrUpdate contact {}", simpleContact);
            contactRepository.save(simpleContact);
            LOGGER.info("successfully saved contact {}", simpleContact);
        }

        LOGGER.info("successfully saved {} contacts", simpleContacts.size());
    }


    @GetMapping(value = "/contact")
    public List<SimpleContact> getAllContacts() {
        LOGGER.info("find all contacts from db");
        List<SimpleContact> simpleContacts = contactRepository.findAll();
        LOGGER.info("number of contacts found fromm db: {}", simpleContacts.size());

        return simpleContacts;
    }

    @DeleteMapping(value = "/contact/{id}")
    public void deleteContact(@PathVariable Integer id) {
        LOGGER.info("deleting contact, id = {}", id);

        SimpleContact simpleContact = contactRepository.findById(id).get();

        if (simpleContact == null) {
            LOGGER.warn("contact {} not found, skip deleting", id);

            return;
        }

        contactRepository.deleteById(id);
        LOGGER.info("successfully deleted contact {}", simpleContact);
    }
}
