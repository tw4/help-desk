package buzzspire.helpdesk.business.abstracts;

import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.user.UpdateUserBasicInfo;
import buzzspire.helpdesk.dto.request.user.UpdateUserPasswordRequest;
import buzzspire.helpdesk.dto.request.user.UserRequest;
import buzzspire.helpdesk.entities.concreates.User;

import java.util.List;

public interface UserServices {
    Result add(UserRequest user);
    Result delete(long id);
    Result updateBasicInfo(long id,UpdateUserBasicInfo user);
    Result updatePassword(long id, UpdateUserPasswordRequest password);
    ResultData<List<User>> getAll();
    ResultData<User> getById(long id);
}
