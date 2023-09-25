package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.TicketStatusServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.TicketStatusRequest.TicketStatusRequest;
import buzzspire.helpdesk.entities.concreates.TicketStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Tag(name = "Ticket status APIs")
@RestController
@RequestMapping("/api/status")
public class TicketStatusController {
    private final TicketStatusServices ticketStatusServices;

    public TicketStatusController(TicketStatusServices ticketStatusServices) {
        this.ticketStatusServices = ticketStatusServices;
    }

    @Operation(summary = "Get all ticket status")
    @GetMapping("/")
    public ResultData<List<TicketStatus>> getAll() {
        return ticketStatusServices.getAll();
    }

    @Operation(summary = "add new ticket status")
    @PostMapping("/")
    public Result add(@RequestBody TicketStatusRequest request) {
        return ticketStatusServices.add(request.getStatus());
    }


    @Operation(summary = "delete ticket status")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id) {
        return ticketStatusServices.delete(id);
    }

}
