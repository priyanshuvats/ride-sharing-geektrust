package ride.sharing.geektrust.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Ride {
    private final String id;
    private final Rider rider;
    private final Driver driver;
    private int sourcexc;
    private int sourceyc;
    private int destinationxc;
    private int destinationyc;
    private boolean isOngoing;
    private int duration;
}
