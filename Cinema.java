package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Cinema {
    private int total_rows;
    private int total_columns;
    private List<Seat> available_seats;

    @JsonIgnore
    private Map<String, Seat> ticket;

    public Cinema(int rows, int cols) {
        this.total_rows = rows;
        this.total_columns = cols;
        this.available_seats = new ArrayList<>();

        for (int i=1; i<=rows; i++) {
            for (int j=1; j<=cols; j++) {
                available_seats.add(new Seat(i, j));
            }
        }

        this.ticket = new HashMap<>();
    }

    public void newTicket(UUID token, Seat seat) {
        ticket.put(token.toString(), seat);
    }
}
