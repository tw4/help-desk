package buzzspire.helpdesk.dto.request.TicketMassage;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@Getter
@NoArgsConstructor
@NotNull
public class TicketMassageRequest {
    private String message;
    private Date date;
    @ManyToOne
    private Long senderId;
    @ManyToOne
    private long ticketId;
}
