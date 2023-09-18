package buzzspire.helpdesk.entities.concreates;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tickets")
public class Ticket {
    @GeneratedValue(generator = "increment")
    @Id
    private long id;
    private String title;
    private String description;
    @ManyToOne
    private TicketStatus status;
    private String priority;
    private Date date;
    @ManyToOne
    private User userId;
    private String assignedTo;
    @OneToMany
    private List<Message> message;
}
