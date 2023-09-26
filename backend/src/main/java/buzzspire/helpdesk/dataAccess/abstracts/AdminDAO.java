package buzzspire.helpdesk.dataAccess.abstracts;

import buzzspire.helpdesk.entities.concreates.Admin;
import buzzspire.helpdesk.entities.concreates.Support;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDAO extends JpaRepository<Admin, Long> {
    boolean existsByUserId(long Id);
    Admin findByUserId(long Id);

}
