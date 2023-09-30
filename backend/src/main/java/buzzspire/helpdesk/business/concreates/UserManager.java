package buzzspire.helpdesk.business.concreates;

import buzzspire.helpdesk.business.abstracts.UserServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dataAccess.abstracts.UserDAO;
import buzzspire.helpdesk.dto.request.user.UpdateUserBasicInfo;
import buzzspire.helpdesk.dto.request.user.UpdateUserPasswordRequest;
import buzzspire.helpdesk.dto.request.user.UserRequest;
import buzzspire.helpdesk.entities.concreates.RoleEnum;
import buzzspire.helpdesk.entities.concreates.User;
import buzzspire.helpdesk.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserServices {
    private final UserDAO userDAO;
    private final JwtTokenProvider jwtTokenProvider;

    public UserManager(UserDAO userDAO, JwtTokenProvider jwtTokenProvider) {
        this.userDAO = userDAO;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Result add(UserRequest user, String token) {
        String role = jwtTokenProvider.getRoleFromToken(token);
        if(!role.equals(RoleEnum.ADMIN.toString())){
            return new Result(false, "You are not authorized");
        }

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
    public Result delete(long id, String token) {
        String role = jwtTokenProvider.getRoleFromToken(token);
        if(!role.equals(RoleEnum.ADMIN.toString())){
            return new Result(false, "You are not authorized");
        }

        try {
            userDAO.delete(userDAO.getUserById(id));
        } catch (Exception e) {
            return new Result(false, "User not deleted");
        }
        return new Result(true, "User deleted");
    }

    @Override
    public Result updateBasicInfo(UpdateUserBasicInfo user, String token) {
        long id = jwtTokenProvider.getIdFromToken(token);
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
    public Result updatePassword(UpdateUserPasswordRequest request, String token) {
        long id = jwtTokenProvider.getIdFromToken(token);
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
    public ResultData<List<User>> getAll(String token) {
        String role = jwtTokenProvider.getRoleFromToken(token);
        if(!role.equals(RoleEnum.ADMIN.toString()) && !role.equals(RoleEnum.SUPPORT.toString())){
            return new ResultData<>(null, "You are not authorized", false);
        }
        return new ResultData<>(userDAO.findAll(), "All user list",true);
    }

    @Override
    public ResultData<User> getById(long id, String token) {
        String role = jwtTokenProvider.getRoleFromToken(token);
        if(!role.equals(RoleEnum.ADMIN.toString()) && !role.equals(RoleEnum.SUPPORT.toString())){
            return new ResultData<>(null, "You are not authorized", false);
        }
        return new ResultData<>(userDAO.getUserById(id), "User", true);
    }

    @Override
    public ResultData<String> login(String email, String password) {
        User user = userDAO.findByEmailAndPassword(email, password);
        if(user == null ){
            return new ResultData<>(null, "email or password wrong", false);
        }
        String token = jwtTokenProvider.createToken(user);
        return new ResultData<>(token, "Login successful", true);
    }

    @Override
    public Result verifyToken(String token) {
        if(jwtTokenProvider.validateToken(token)){
            return new Result(true, "Token is valid");
        }
        return new Result(false, "Token is not valid");
    }

    @Override
    public ResultData<User> getByEmail(String email) {
        return new ResultData<>(userDAO.findByEmail(email), "User", true);
    }

    @Override
    public ResultData<User> getMyInfo(String token) {
        long id = jwtTokenProvider.getIdFromToken(token);
        return new ResultData<>(userDAO.getUserById(id), "User", true);
    }
}
