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
    public Result add(@RequestBody UserRequest user) {
        return userServices.add(user);
    }

    @Operation(summary = "Get All Users")
    @GetMapping("/")
    public ResultData<List<User>> getAll() {
        return userServices.getAll();
    }

    @Operation(summary = "Delete User")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id) {
        return userServices.delete(id);
    }


    @Operation(summary = "Update User Basic Info")
    @PutMapping("/{id}")
    public Result updateBasicInfo(@PathVariable long id,@RequestBody UpdateUserBasicInfo user) {
        return userServices.updateBasicInfo(id,user);

    }

    @Operation(summary = "Update User Password")
    @PutMapping("/{id}/password")
    public Result updatePassword(@PathVariable long id,@RequestBody UpdateUserPasswordRequest user) {
        return userServices.updatePassword(id,user);
    }

    @Operation(summary = "Get User By Id")
    @GetMapping("/{id}")
    public ResultData<User> getById(@PathVariable Long id) {
        return userServices.getById(id);
    }
}
