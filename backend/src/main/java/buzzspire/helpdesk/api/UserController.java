package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.UserServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.user.*;
import buzzspire.helpdesk.entities.concreates.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
@Tag(name = "User APIs")
public class UserController {
    private final UserServices userServices;
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @Operation(summary = "Add User")
    @PostMapping("/")
    public Result add(@RequestBody UserRequest user, @RequestHeader String token) {
        return userServices.add(user, token);
    }

    @Operation(summary = "Get All Users")
    @GetMapping("/")
    public ResultData<List<User>> getAll(@RequestHeader String token) {
        return userServices.getAll(token);
    }

    @Operation(summary = "Delete User")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id, @RequestHeader String token) {
        return userServices.delete(id, token);
    }


    @Operation(summary = "Update User Basic Info")
    @PutMapping("/{id}")
    public Result updateBasicInfo(@RequestBody UpdateUserBasicInfo user, @RequestHeader String token) {
        return userServices.updateBasicInfo(user, token);

    }

    @Operation(summary = "Update User Password")
    @PutMapping("/{id}/password")
    public Result updatePassword(@RequestBody UpdateUserPasswordRequest user, @RequestHeader String token) {
        return userServices.updatePassword(user, token);
    }

    @Operation(summary = "Get User By Id")
    @GetMapping("/{id}")
    public ResultData<User> getById(@PathVariable Long id, @RequestHeader String token) {
        return userServices.getById(id, token);
    }

    @Operation
    @GetMapping("/me")
    public ResultData<User> getMyInfo(@RequestHeader String token) {
        return userServices.getMyInfo(token);
    }
}
