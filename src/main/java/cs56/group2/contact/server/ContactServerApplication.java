package cs56.group2.contact.server;

import cs56.group2.contact.server.model.Contact;
import cs56.group2.contact.server.repository.ContactRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactServerApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactServerApplication.class);

    @Autowired
    ContactRepository contactRepository;

    public static void main(String[] args) {
        SpringApplication.run(ContactServerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Application started");
        LOGGER.info("prepopulate some contact for demonstration purpose");

        Contact contact1 = new Contact();
        contact1.setFirstName("Brandon");
        contact1.setLastName("Zhang");
        contact1.setCell("424-000-0001");
        contact1.setEmail("yuxiang.brandon.zhang@gmail.com");
        contact1.setMiddleName("");

        Contact contact2 = new Contact();
        contact2.setFirstName("Chris");
        contact2.setLastName("Smith");
        contact2.setCell("424-000-0002");
        contact2.setEmail("chris.smith@gmail.com");
        contact2.setMiddleName("Jackson");

        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        contacts.add(contact2);

        contactRepository.saveAll(contacts);

        LOGGER.info("successfully prepopulate following contacts to H2 database {}", contacts);

    }

}
