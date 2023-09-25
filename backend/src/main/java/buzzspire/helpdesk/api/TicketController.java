package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.TicketServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.ticket.TicketRequest;
import buzzspire.helpdesk.entities.concreates.Ticket;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Tag(name = "Ticket APIs")
@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketServices ticketServices;

    public TicketController(TicketServices ticketServices) {
        this.ticketServices = ticketServices;
    }


    @Operation(summary = "Get All Ticket")
    @GetMapping("/")
    public ResultData<List<Ticket>> getTicket() {
        return ticketServices.getAllTicket();
    }

    @Operation(summary = "Add Ticket")
    @PostMapping("/")
    public Result addTicket(@RequestBody TicketRequest request) {
        return ticketServices.addTicket(request);
    }

    @Operation(summary = "get Ticket By Id")
    @GetMapping("/{id}")
    public ResultData<Ticket> getTicketById(@PathVariable long id) {
        return ticketServices.getTicketById(id);
    }

    @Operation(summary = "get Ticket By User Id")
    @GetMapping("/user/{id}")
    public ResultData<List<Ticket>> getTicketByUserId(@PathVariable long id) {
        return ticketServices.getTicketByUserId(id);
    }

    @Operation(summary = "delete Ticket By Id")
    @DeleteMapping("/{id}")
    public Result deleteTicket(@PathVariable long id) {
        return ticketServices.deleteTicket(id);
    }


    @Operation(summary = "add Ticket Assignee")
    @PutMapping("/{ticketId}/assignee/{assigneeId}")
    public Result addTicketAssignee(@PathVariable long ticketId, @PathVariable long assigneeId) {
        return ticketServices.addTicketAssignee(ticketId, assigneeId);
    }

    @Operation(summary = "remove Ticket Assignee")
    @DeleteMapping("/{ticketId}/assignee/")
    public Result removeTickerAssignee(@PathVariable long ticketId) {
        return ticketServices.removeTickerAssignee(ticketId);
    }

}
