package cinema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seat {
    private int row;
    private int column;

    public Seat(int row, int col) {
        this.row = row;
        this.column = col;
    }
}
