package ride.sharing.geektrust.entities;

import lombok.Getter;
import lombok.Setter;
import ride.sharing.geektrust.dto.Coordinates;
import ride.sharing.geektrust.exceptions.DriverAlreadyBookedException;

@Getter
@Setter
public class Driver {
    private final String id;
    private boolean isBooked = false;
    private Coordinates coordinates;

    public Driver(String id, Coordinates coordinates) {
        this.id = id;
        this.coordinates = coordinates;
    }

    public synchronized void book() throws DriverAlreadyBookedException{
        if(isBooked){
            throw new DriverAlreadyBookedException("Driver is already booked!!");
        }
        isBooked = true;
    }
}