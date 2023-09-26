package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.SupportServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.dto.request.SupportRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Tag(name = "Support APIs")
@RequestMapping("/api/support")
@RestController
public class SupportController {
    private final SupportServices supportServices;

    public SupportController(SupportServices supportServices) {
        this.supportServices = supportServices;
    }

    @Operation(summary = "Add support")
    @PostMapping("/")
    public Result addSupport(@RequestBody SupportRequest request) {
        return supportServices.addSupport(request);
    }

    @Operation(summary = "Remove support")
    @DeleteMapping("/")
    public Result removeSupport(@RequestBody SupportRequest request) {
        return supportServices.removeSupport(request);
    }

    @Operation(summary = "Auth support")
    @GetMapping("/{id}")
    public Result AuthSupport(@PathVariable long id) {
        return supportServices.AuthSupport(id);
    }

}
