package buzzspire.helpdesk.dto.request.user;


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
    private String role;
    private String title;
}
