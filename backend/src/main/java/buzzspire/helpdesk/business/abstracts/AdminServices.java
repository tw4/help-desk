package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.Admin.AdminRequest;

import java.util.List;

public interface AdminServices {
    Result addAdmin(AdminRequest adminRequest);
    Result removeAdmin(AdminRequest adminRequest);
    ResultData<Boolean> AuthAdmin(long userId);
}
