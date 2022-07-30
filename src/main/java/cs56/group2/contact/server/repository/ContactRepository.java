package cs56.group2.contact.server.repository;

import cs56.group2.contact.server.model.SimpleContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<SimpleContact, Integer> {

}
