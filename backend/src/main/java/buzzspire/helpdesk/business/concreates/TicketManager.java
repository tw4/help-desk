package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.TicketServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.TicketDAO;
import buzzspire.helpdesk.dto.request.ticket.TicketRequest;
import buzzspire.helpdesk.entities.concreates.Ticket;
import buzzspire.helpdesk.entities.concreates.TicketPriority;
import buzzspire.helpdesk.entities.concreates.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketManager implements TicketServices {

    private final TicketDAO ticketDAO;

    public TicketManager(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public Result addTicket(TicketRequest ticket) {
        try {
            this.ticketDAO.save(Ticket.builder()
                    .title(ticket.getTitle())
                    .description(ticket.getDescription())
                    .priority(TicketPriority.builder().id(ticket.getPriority()).build())
                    .user(User.builder().id(ticket.getUser()).build())
                    .status(ticket.getStatus())
                    .date(ticket.getDate())
                    .build());
        } catch (Exception e) {
            return new Result(false, "Ticket not added");
        }
        return new Result(true, "Ticket added");
    }

    @Override
    public Result deleteTicket(long id) {
        try {
            this.ticketDAO.deleteById(id);
        } catch (Exception e) {
            return new Result(false, "Ticket not deleted");
        }
        return new Result(true, "Ticket deleted");
    }

    @Override
    public ResultData<List<Ticket>> getAllTicket() {
        return new ResultData<>(ticketDAO.findAll(), "Ticket list", true);
    }

    @Override
    public ResultData<Ticket> getTicketById(long id) {
        return new ResultData<>(ticketDAO.findById(id).get(), "Ticket found", true);
    }

    @Override
    public ResultData<List<Ticket>> getTicketByUserId(long id) {
        return new ResultData<>(ticketDAO.findAllByUserId(id), "Ticket list", true);
    }

    @Override
    public Result addTicketAssignee(long ticketId, long assigneeId) {
        Ticket newTicket = ticketDAO.findById(ticketId).get();
        newTicket.setAssignedTo(User.builder().id(assigneeId).build());

        try{
            ticketDAO.save(newTicket);
        } catch (
                Exception e) {
            return new Result(false, "Ticket not added");
        }

        return new Result(true, "Ticket assignee added");
    }

    @Override
    public Result removeTickerAssignee(long ticketId) {
        Ticket newTicket = ticketDAO.findById(ticketId).get();
        newTicket.setAssignedTo(null);
        try {
            ticketDAO.save(newTicket);
        } catch (Exception e) {
            return new Result(false, "Ticket not added");
        }
        return new Result(true, "Ticket assignee removed");
    }

}
