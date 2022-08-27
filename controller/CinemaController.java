package cinema.controller;

import cinema.Cinema;
import cinema.Seat;
import cinema.exception.InvalidSeatException;
import cinema.exception.TicketAlreadyPurchasedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CinemaController {

    Cinema cinema = new Cinema(9, 9);

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Seat seat) {
        Map<String, String> response;
        if(!seat.isValid(cinema)) {
            response = new HashMap<>(Map.of("error", "The number of a row or a column is out of bounds!"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        for (Seat s : cinema.getAvailable_seats()) {
            if(seat.getRow()==s.getRow() && seat.getColumn()==s.getColumn()) {
                cinema.getAvailable_seats().remove(s);
                return new ResponseEntity<>(s, HttpStatus.OK);
            }
        }

        response = new HashMap<>(Map.of("error", "The ticket has been already purchased!"));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
