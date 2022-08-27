package cinema;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cinema {
    private int total_rows;
    private int total_columns;
    private List<Seat> available_seats;

    public Cinema(int rows, int cols) {
        this.total_rows = rows;
        this.total_columns = cols;
        this.available_seats = new ArrayList<>();

        for (int i=1; i<=rows; i++) {
            for (int j=1; j<=cols; j++) {
                available_seats.add(new Seat(i, j));
            }
        }
    }
}
