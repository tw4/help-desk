package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.UserServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.auth.AuthLoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth Controller", description = "Auth API Endpoints")
public class AuthController {

    private final UserServices userServices;

    public AuthController(UserServices userServices) {
        this.userServices = userServices;
    }

    @Operation(summary = "Login")
    @PostMapping("/")
    public ResultData<String> login(@RequestBody AuthLoginRequest request) {
        return userServices.login(request.getEmail(), request.getPassword());
    }

    @Operation(summary = "Verify Token")
    @GetMapping("/")
    public Result verifyToken(@RequestHeader String token) {
        return userServices.verifyToken(token);
    }
}
