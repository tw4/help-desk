package buzzspire.helpdesk.entities.concreates;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Message {
    @GeneratedValue(generator = "increment")
    @Id
    private long id;
    private String message;
    private Date date;
    @ManyToOne
    private User sender;
    private String receiver;
    @ManyToOne
    private Ticket ticket;
}