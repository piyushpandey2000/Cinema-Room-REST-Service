package cinema.controller;

import cinema.Cinema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {

    @GetMapping("/seats")
    public Cinema getSeats() {
        return new Cinema(9, 9);
    }
}
