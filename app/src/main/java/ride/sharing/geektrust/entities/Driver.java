package ride.sharing.geektrust.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Driver {
    private final String id;
    private boolean isBooked = false;
    private int xc;
    private int yc;

    public Driver(String id, int xc, int yc) {
        this.id = id;
        this.xc = xc;
        this.yc = yc;
    }
}