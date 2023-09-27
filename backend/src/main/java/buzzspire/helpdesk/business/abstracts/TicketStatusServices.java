package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.entities.concreates.TicketStatus;

import java.util.List;

public interface TicketStatusServices {
    ResultData<List<TicketStatus>> getAll();
    Result add(String status);
    Result delete(long id);

}
