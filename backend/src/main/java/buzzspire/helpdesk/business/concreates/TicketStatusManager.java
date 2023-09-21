package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.TicketStatusServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.TicketStatusDAO;
import buzzspire.helpdesk.entities.concreates.TicketStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketStatusManager implements TicketStatusServices {
    private final TicketStatusDAO ticketStatusDAO;

    public TicketStatusManager(TicketStatusDAO ticketStatusDAO) {
        this.ticketStatusDAO = ticketStatusDAO;
    }

    @Override
    public ResultData<List<TicketStatus>> getAll() {
        return new ResultData<>(this.ticketStatusDAO.findAll(), "ticket status list", true);
    }

    @Override
    public Result add(String status) {
        try {
            this.ticketStatusDAO.save(TicketStatus.builder().status(status).build());
        } catch (Exception e) {
            return new Result(false, "Ticket Status Not Added");
        }
        return new Result(true, "Ticket Status Added");
    }

    @Override
    public Result delete(long id) {
        try {
            this.ticketStatusDAO.deleteById(id);
        } catch (Exception e) {
            return new Result(false, "Ticket Status Not Deleted");
        }
        return new Result(true, "Ticket Status Deleted");
    }

}
