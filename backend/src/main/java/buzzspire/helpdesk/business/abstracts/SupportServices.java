package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.SupportRequest;

public interface SupportServices {
    Result addSupport(SupportRequest supportRequest);
    Result removeSupport(SupportRequest supportRequest);
    ResultData<Boolean> AuthSupport(long userId);
}
