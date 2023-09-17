package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.UserServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.UserDAO;
import buzzspire.helpdesk.dto.request.user.UpdateUserBasicInfo;
import buzzspire.helpdesk.dto.request.user.UpdateUserPasswordRequest;
import buzzspire.helpdesk.dto.request.user.UserRequest;
import buzzspire.helpdesk.entities.concreates.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserServices {
    private final UserDAO userDAO;
    public UserManager(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Result add(UserRequest user) {
        User newUser = User.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .title(user.getTitle())
                .build();
        try {
            userDAO.save(newUser);
        } catch (Exception e) {
            return new Result(false, "User not added");
        }
        return new Result(true, "User added");
    }

    @Override
    public Result delete(long id) {
        try {
            userDAO.delete(userDAO.getUserById(id));
        } catch (Exception e) {
            return new Result(false, "User not deleted");
        }
        return new Result(true, "User deleted");
    }

    @Override
    public Result updateBasicInfo(long id,UpdateUserBasicInfo user) {
        User newUser = userDAO.getUserById(id);
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setEmail(user.getEmail());
        newUser.setRole(user.getRole());
        newUser.setTitle(user.getTitle());
        try {
            userDAO.save(newUser);
        } catch (Exception e) {
            return new Result(false, "User not updated");
        }
        return new Result(true, "User updated");
    }

    @Override
    public Result updatePassword(long id, UpdateUserPasswordRequest request) {
        User newUser = userDAO.getUserById(id);
        newUser.setPassword(request.getPassword());
        try {
            userDAO.save(newUser);
        } catch (Exception e) {
            return new Result(false, "User not updated");
        }
        return new Result(true, "User updated");
    }

    @Override
    public ResultData<List<User>> getAll() {
        return new ResultData<>(userDAO.findAll(), "All user list",true);
    }

    @Override
    public ResultData<User> getById(long id) {
        return new ResultData<>(userDAO.getUserById(id), "User", true);
    }
}
