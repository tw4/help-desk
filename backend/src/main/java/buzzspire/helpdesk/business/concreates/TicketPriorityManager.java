package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.TicketPriorityServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.TicketPriorityDAO;
import buzzspire.helpdesk.entities.concreates.TicketPriority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketPriorityManager implements TicketPriorityServices {
    private TicketPriorityDAO ticketPriorityDAO;

    public TicketPriorityManager(TicketPriorityDAO ticketPriorityDAO) {
        this.ticketPriorityDAO = ticketPriorityDAO;
    }

    @Override
    public ResultData<List<TicketPriority>> getAll() {
        return new ResultData<>(this.ticketPriorityDAO.findAll(), "Ticket priority list", true);
    }

    @Override
    public Result add(String priority) {
        try{
            this.ticketPriorityDAO.save(TicketPriority.builder().priority(priority).build());
        } catch (Exception e) {
            return new Result(false, "Ticket priority not added");
        }

        return new Result(true, "Ticket priority added");
    }

    @Override
    public Result delete(long id) {
        return null;
    }
}
