package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.UserRoleServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.UserRole.UserRoleRequest;
import buzzspire.helpdesk.entities.concreates.UserRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Role APIs")
public class UserRoleController {
    private final UserRoleServices userRoleService;

    public UserRoleController(UserRoleServices userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Operation(summary = "Get All Roles")
    @GetMapping("/")
    public ResultData<List<UserRole>> getAll() {
        return userRoleService.getAll();
    }

    @Operation(summary = "Add Role")
    @PostMapping("/")
    public Result add(@RequestBody UserRoleRequest request) {
        return userRoleService.add(request.getRole());
    }

    @Operation(summary = "Update Role")
    @PutMapping("/{id}")
    public Result update(@PathVariable long id,@RequestBody UserRoleRequest request) {
        return userRoleService.update(id, request.getRole());
    }

    @Operation(summary = "Delete Role")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id) {
        return userRoleService.delete(id);
    }
}
