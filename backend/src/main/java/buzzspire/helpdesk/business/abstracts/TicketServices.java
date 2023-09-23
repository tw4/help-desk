package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.ticket.TicketRequest;
import buzzspire.helpdesk.entities.concreates.Ticket;

import java.util.List;

public interface TicketServices {
    Result addTicket(TicketRequest ticket);
    Result deleteTicket(long id);
    ResultData<List<Ticket>> getAllTicket();
    ResultData<Ticket> getTicketById(long id);
    ResultData<List<Ticket>> getTicketByUserId(long id);
    Result addTicketAssignee(long ticketId, long assigneeId);
    Result removeTickerAssignee(long ticketId);
}