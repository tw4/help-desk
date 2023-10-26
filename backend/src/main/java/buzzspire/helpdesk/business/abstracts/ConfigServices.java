package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.Config.AddConfigRequest;
import buzzspire.helpdesk.entities.concreates.Config;

import java.util.List;

public interface ConfigServices {
    Result add(String token, AddConfigRequest addConfigRequest);
    ResultData<List<Config>> getAll(String token);
    Result delete(String token, long id);
}
