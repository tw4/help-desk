package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.entities.concreates.UserRole;

import java.util.List;

public interface UserRoleServices {
    ResultData<List<UserRole>> getAll();
    Result add(String role);
    Result update(long id, String role);
    Result delete(long id);
}
