package buzzspire.helpdesk.api;

import buzzspire.helpdesk.business.abstracts.UserTitleServices;
import buzzspire.helpdesk.core.utilities.result.Result;
import buzzspire.helpdesk.core.utilities.result.ResultData;
import buzzspire.helpdesk.dto.request.UserTitle.UserTitleRequest;
import buzzspire.helpdesk.entities.concreates.UserTitle;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/titles")
@Tag(name = "User Title APIs")
public class UserTitleController {

    private final UserTitleServices userTitleServices;

    public UserTitleController(UserTitleServices userTitleServices) {
        this.userTitleServices = userTitleServices;
    }

    @Operation(summary = "Get All User Titles")
    @GetMapping("/")
    public ResultData<List<UserTitle>> getAll() {
        return userTitleServices.getAll();
    }

    @Operation(summary = "Add User Title")
    @PostMapping("/")
    public Result add(@RequestBody UserTitleRequest request) {
        System.out.println(request.getTitle());
        return userTitleServices.add(request.getTitle());
    }

    @Operation(summary = "Update User Title")
    @PutMapping("/{id}")
    public Result update(@PathVariable int id, @RequestBody UserTitleRequest request) {
        return userTitleServices.update(id, request.getTitle());
    }

    @Operation(summary = "Delete User Title")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        return userTitleServices.delete(id);
    }
}
