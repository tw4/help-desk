package buzzspire.helpdesk.dto.request.user;


import buzzspire.helpdesk.entities.concreates.UserRole;
import buzzspire.helpdesk.entities.concreates.UserTitle;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@NotNull
public class UpdateUserBasicInfo {
    private String name;
    private String surname;
    private String email;
    private UserRole role;
    private UserTitle title;
}
