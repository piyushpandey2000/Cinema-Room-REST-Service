package cinema.controller;

import cinema.Cinema;
import cinema.Seat;
import cinema.exception.InvalidSeatException;
import cinema.exception.TicketAlreadyPurchasedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class CinemaController {

    Cinema cinema = new Cinema(9, 9);

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Seat seat) {
        Map<String, Object> response;
        if(!seat.isValid(cinema)) {
            response = new HashMap<>(Map.of("error", "The number of a row or a column is out of bounds!"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        for (Seat s : cinema.getAvailable_seats()) {
            if(seat.getRow()==s.getRow() && seat.getColumn()==s.getColumn()) {
                cinema.getAvailable_seats().remove(s);

                UUID token = UUID.randomUUID();
                cinema.newTicket(token, s);

                response = new HashMap<>(Map.of("token", token, "ticket", s));
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }

        response = new HashMap<>(Map.of("error", "The ticket has been already purchased!"));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        Map<String, Object> response;

        if(cinema.getTicket().containsKey(token)) {
            Seat returned_ticket = cinema.getTicket().get(token);
            cinema.getTicket().remove(token);
            cinema.getAvailable_seats().add(returned_ticket);

            response = new HashMap<>(Map.of("returned_ticket", returned_ticket));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response = new HashMap<>(Map.of("error", "Wrong token!"));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
