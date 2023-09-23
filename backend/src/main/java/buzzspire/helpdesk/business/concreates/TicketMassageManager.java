package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.TicketMassageServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.TicketMassageDAO;
import buzzspire.helpdesk.dto.request.TicketMassage.TicketMassageRequest;
import buzzspire.helpdesk.entities.concreates.Ticket;
import buzzspire.helpdesk.entities.concreates.TicketMassage;
import buzzspire.helpdesk.entities.concreates.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketMassageManager implements TicketMassageServices {
    private final TicketMassageDAO ticketMassageDAO;

    public TicketMassageManager(TicketMassageDAO ticketMassageDAO) {
        this.ticketMassageDAO = ticketMassageDAO;
    }

    @Override
    public ResultData<List<TicketMassage>> getAll() {
        return new ResultData<>(ticketMassageDAO.findAll(), "Ticket Messages listed", true);
    }

    @Override
    public Result add(TicketMassageRequest TicketMessage) {
        TicketMassage ticketMassage = TicketMassage.builder()
                .message(TicketMessage.getMessage())
                .date(TicketMessage.getDate())
                .sender(User.builder().id(TicketMessage.getSenderId()).build())
                .ticket(Ticket.builder().id(TicketMessage.getTicketId()).build())
                .build();

        try {
            ticketMassageDAO.save(ticketMassage);
        } catch (Exception e) {
            return new Result(false, "Ticket Message could not be added");
        }
        return new Result(true, "Ticket Message added");
    }

    @Override
    public Result delete(long id) {
        try {
            ticketMassageDAO.deleteById(id);
        } catch (Exception e) {
            return new Result(false, "Ticket Message could not be deleted");
        }
        return new Result(true, "Ticket Message deleted");
    }

    @Override
    public ResultData<TicketMassage> getById(long id) {
        return new ResultData<>(ticketMassageDAO.findById(id).orElse(null), "Ticket Message listed", true);}
}