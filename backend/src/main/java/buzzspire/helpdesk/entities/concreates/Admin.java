package buzzspire.helpdesk.entities.concreates;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {
    @GeneratedValue(generator = "increment")
    @Id
    private long id;
    @ManyToOne
    private User user;
}
