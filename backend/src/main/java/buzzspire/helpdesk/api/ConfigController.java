package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.ConfigServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.dto.request.Config.AddConfigRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/config")
public class ConfigController {

    // inject ConfigServices
    private final ConfigServices configServices;

    // constructor injection
    public ConfigController(ConfigServices configServices) {
        this.configServices = configServices;
    }

    // add config
    @PostMapping("/")
    public Result saveConfig(@RequestHeader String token, @RequestBody AddConfigRequest addConfigRequest) {
        return configServices.add(token, addConfigRequest);
    }

    // get all config
    @GetMapping("/")
    public String getConfig(@RequestHeader String token) {
        return "get all Config";
    }

    // delete config
    @DeleteMapping("/")
    public String deleteConfig(@RequestHeader String token) {
        return "Config deleted";
    }
}
