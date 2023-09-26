package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.AdminServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.dto.request.Admin.AdminRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Tag(name = "Admin APIs")
@RestController
@RequestMapping("/api/admins")
public class AdminController {
    private final AdminServices adminServices;

    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @Operation(summary = "Add Admin")
    @PostMapping("/")
    public Result addAdmin(@RequestBody AdminRequest  request) {
        return adminServices.addAdmin(request);
    }

    @Operation(summary = "Remove Admin")
    @DeleteMapping("/")
    public Result removeAdmin(@RequestBody AdminRequest request) {
        return adminServices.removeAdmin(request);
    }

    @Operation(summary = "Auth Admin")
    @GetMapping("/{id}")
    public Result AuthAdmin(@PathVariable long id) {
        return adminServices.AuthAdmin(id);
    }

}
