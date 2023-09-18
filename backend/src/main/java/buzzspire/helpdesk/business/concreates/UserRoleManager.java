package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.UserRoleServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.UserRoleDAO;
import buzzspire.helpdesk.entities.concreates.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleManager implements UserRoleServices {
    private UserRoleDAO userRoleDAO;

    public UserRoleManager(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    @Override
    public ResultData<List<UserRole>> getAll() {
        return new ResultData<>(this.userRoleDAO.findAll(), "user role list ", true);
    }

    @Override
    public Result add(String role) {
        try {
            this.userRoleDAO.save(UserRole.builder()
                    .role(role).build());
            return new Result(true, "user role added");
        } catch (Exception e) {
            return new Result(false, "user role not added");
        }
    }

    @Override
    public Result update(long id, String role) {
        UserRole newUserRole = this.userRoleDAO.findById(id).get();
        newUserRole.setRole(role);
        try
        {
            this.userRoleDAO.save(newUserRole);
            return new Result(true, "user role updated");
        } catch (Exception e) {
            return new Result(false, "user role not updated");
        }
    }

    @Override
    public Result delete(long id) {
        try {
            this.userRoleDAO.deleteById(id);
            return new Result(true, "user role deleted");
        } catch (Exception e) {
            return new Result(false, "user role not deleted");
        }
    }
}
