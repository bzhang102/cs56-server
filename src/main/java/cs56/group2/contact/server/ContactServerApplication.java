package cs56.group2.contact.server;

import cs56.group2.contact.server.model.SimpleContact;
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

        SimpleContact simpleContact1 = new SimpleContact();
        simpleContact1.setFirstName("Brandon");
        simpleContact1.setLastName("Zhang");
        simpleContact1.setCell("424-000-0001");
        simpleContact1.setEmail("yuxiang.brandon.zhang@gmail.com");
        simpleContact1.setMiddleName("");

        SimpleContact simpleContact2 = new SimpleContact();
        simpleContact2.setFirstName("Chris");
        simpleContact2.setLastName("Smith");
        simpleContact2.setCell("424-000-0002");
        simpleContact2.setEmail("chris.smith@gmail.com");
        simpleContact2.setMiddleName("Jackson");

        List<SimpleContact> simpleContacts = new ArrayList<>();
        simpleContacts.add(simpleContact1);
        simpleContacts.add(simpleContact2);

        contactRepository.saveAll(simpleContacts);

        LOGGER.info("successfully prepopulate following contacts to H2 database {}", simpleContacts);

    }

}
