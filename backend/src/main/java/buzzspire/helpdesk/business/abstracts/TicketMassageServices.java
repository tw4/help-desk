package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.TicketMassage.TicketMassageRequest;
import buzzspire.helpdesk.entities.concreates.TicketMassage;

import java.util.List;

public interface TicketMassageServices {
    ResultData<List<TicketMassage>> getAll();
    Result add(TicketMassageRequest TicketMessage);
    Result delete(long id);
    ResultData<TicketMassage> getById(long id);
}
