package buzzspire.helpdesk.dataAccess.abstracts;

import buzzspire.helpdesk.entities.concreates.TicketMassage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketMassageDAO extends JpaRepository<TicketMassage, Long> {
    List<TicketMassage> findAllByTicketId(long id);
}
