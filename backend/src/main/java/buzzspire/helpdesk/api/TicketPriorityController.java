package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.TicketPriorityServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.TicketPriority.TicketPriorityRequest;
import buzzspire.helpdesk.entities.concreates.TicketPriority;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ticket Priority APIs")
@RestController
@RequestMapping("/api/priority")
public class TicketPriorityController {
    private final TicketPriorityServices ticketPriorityServices;

    public TicketPriorityController(TicketPriorityServices ticketPriorityServices) {
        this.ticketPriorityServices = ticketPriorityServices;
    }


    @Operation(summary = "Get all ticket priorities")
    @GetMapping("/")
    public ResultData<List<TicketPriority>> getAll() {
        return ticketPriorityServices.getAll();
    }

    @Operation(summary = "Add ticket priority")
    @PostMapping("/")
    public Result add(@RequestBody TicketPriorityRequest request) {
        return ticketPriorityServices.add(request.getPriority());
    }

    @Operation(summary = "Delete ticket priority")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id) {
        return ticketPriorityServices.delete(id);
    }

}
