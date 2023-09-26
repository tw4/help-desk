package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.AdminServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.AdminDAO;
import buzzspire.helpdesk.dto.request.Admin.AdminRequest;
import buzzspire.helpdesk.entities.concreates.Admin;
import buzzspire.helpdesk.entities.concreates.User;
import org.springframework.stereotype.Service;

@Service
public class AdminManager implements AdminServices {
    private final AdminDAO adminDAO;
    private final String secretKey =  "secretKey"; 

    public AdminManager(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public Result addAdmin(AdminRequest adminRequest) {
        if (!adminRequest.getSecretKey().equals(secretKey)){
           return new Result(false, "Secret key is wrong");
        }
        try {
            adminDAO.save(Admin.builder().user(User.builder().id(adminRequest.getUserId()).build()).build());
        } catch (Exception e) {
            return new Result(false, "Admin not added");
        }
        return new Result(true, "Admin added");
    }

    @Override
    public Result removeAdmin(AdminRequest adminRequest) {
        if (!adminRequest.getSecretKey().equals(secretKey)) {
            return new Result(false, "Secret key is wrong");
        }
            try {
            adminDAO.delete(Admin.builder().user(User.builder().id(adminRequest.getUserId()).build()).build());
        } catch (Exception e) {
            return new Result(false, "Admin not removed");
        }
        return new Result(true, "Admin removed");
    }

    @Override
    public ResultData<Boolean> AuthAdmin(long userId) {
        try {
            return new ResultData<>(true, "Admin authenticated", adminDAO.existsByUserId(userId));
        } catch (Exception e) {
            return new ResultData<>(false, "Admin not authenticated", false);
        }
    }
}
