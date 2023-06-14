package ride.sharing.geektrust.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Coordinates {
    private int x;
    private int y;

    public Coordinates clone(){
        return new Coordinates(this.x, this.y);
    }
}
