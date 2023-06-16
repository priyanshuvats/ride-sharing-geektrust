package ride.sharing.geektrust.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ride.sharing.geektrust.dto.Coordinates;
import ride.sharing.geektrust.exceptions.RideAlreadyEndedException;

@Getter
@Setter
@RequiredArgsConstructor
public class Ride {
    private final String id;
    private final Rider rider;
    private final Driver driver;
    private final Coordinates sourceCoordinates;
    private Coordinates destinationCoordinates;
    private boolean hasEnded = false;
    private int duration;

    public void end() throws RideAlreadyEndedException{
        if(this.hasEnded){
            throw new RideAlreadyEndedException("Ride already ended!!");
        }
        this.hasEnded = true;
    }
}
