package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TicketAlreadyPurchasedException extends RuntimeException {

    public TicketAlreadyPurchasedException() {
        super("The ticket has been already purchased!");
    }
    public TicketAlreadyPurchasedException(String msg) {
        super(msg);
    }
}
