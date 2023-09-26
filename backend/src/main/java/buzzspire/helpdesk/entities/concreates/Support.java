package buzzspire.helpdesk.entities.concreates;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supports")
public class Support {
    @GeneratedValue(generator = "increment")
    @Id
    private long id;
    @ManyToOne
    private User user;
}
