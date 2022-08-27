package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Seat {
    private int row;
    private int column;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int price;

    public Seat(int row, int col) {
        this.row = row;
        this.column = col;
        this.price = row>4 ? 8 : 10;
    }

    public boolean isValid(Cinema cinema) {
        return (row >= 1 && row <= cinema.getTotal_rows() && column >= 1 && column <= cinema.getTotal_columns());
    }
}
