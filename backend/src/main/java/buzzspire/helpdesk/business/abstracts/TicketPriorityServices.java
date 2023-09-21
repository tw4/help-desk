package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.entities.concreates.TicketPriority;

import java.util.List;

public interface TicketPriorityServices {
    ResultData<List<TicketPriority>> getAll();
    Result add(String priority);
    Result delete(long id);
}
