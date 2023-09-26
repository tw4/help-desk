package buzzspire.helpdesk.dataAccess.abstracts;

import buzzspire.helpdesk.entities.concreates.Support;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportDAO extends JpaRepository<Support, Long> {
    boolean existsByUserId(long Id);
    Support findByUserId(long Id);
}
