package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.concreates.TicketMassageManager;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.TicketMassage.TicketMassageRequest;
import buzzspire.helpdesk.entities.concreates.TicketMassage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Tag(name = "Ticket Massage APIs")
@RestController
@RequestMapping("/api/massages")
public class TicketMassageController {
   private final TicketMassageManager ticketMassageManager;

    public TicketMassageController(TicketMassageManager ticketMassageManager) {
        this.ticketMassageManager = ticketMassageManager;
    }
    @Operation(summary = "Get all Ticket Massages")
    @GetMapping("/")
    public ResultData<List<TicketMassage>> getAll() {
        return ticketMassageManager.getAll();
    }

    @Operation(summary = "Add Ticket Massage")
    @PostMapping("/")
    public Result add(@RequestBody TicketMassageRequest TicketMessage) {
        return ticketMassageManager.add(TicketMessage);
    }

    @Operation(summary = "Delete Ticket Massage")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id) {
        return ticketMassageManager.delete(id);
    }


    @Operation(summary = "Get all Ticket Massages by massage id")
    @GetMapping("/{id}")
    public ResultData<TicketMassage> getById(@PathVariable long id) {
        return ticketMassageManager.getById(id);
    }

}
