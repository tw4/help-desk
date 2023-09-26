package buzzspire.helpdesk.dto.request.Admin;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@NotNull
public class AdminRequest {
    private long userId;
    private String secretKey;
}
