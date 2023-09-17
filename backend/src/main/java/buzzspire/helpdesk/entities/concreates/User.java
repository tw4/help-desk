package buzzspire.helpdesk.entities.concreates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @GeneratedValue(generator = "increment")
    @Id
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String role;
    private String title;
}
