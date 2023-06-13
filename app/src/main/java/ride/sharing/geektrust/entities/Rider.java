package ride.sharing.geektrust.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Rider {
    private final String id;
    private int xc;
    private int yc;
}
