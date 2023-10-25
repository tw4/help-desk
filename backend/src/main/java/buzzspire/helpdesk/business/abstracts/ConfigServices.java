package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.dto.request.Config.AddConfigRequest;

public interface ConfigServices {
    Result add(String token, AddConfigRequest addConfigRequest);
}
