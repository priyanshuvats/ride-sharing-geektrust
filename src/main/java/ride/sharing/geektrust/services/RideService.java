package ride.sharing.geektrust.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ride.sharing.geektrust.dto.Coordinates;
import ride.sharing.geektrust.entities.Driver;
import ride.sharing.geektrust.entities.Ride;
import ride.sharing.geektrust.entities.Rider;
import ride.sharing.geektrust.exceptions.BadRequestException;
import ride.sharing.geektrust.exceptions.RideAlreadyEndedException;
import ride.sharing.geektrust.repositories.RideRepo;

@AllArgsConstructor
@Service
public class RideService {
    @Autowired
    private final RideRepo rideRepo;

    public void createRide(String rideId, Driver driver, Rider rider,
                            Coordinates sourceCoordinates) throws BadRequestException{
        Ride ride = new Ride(rideId, rider, driver, sourceCoordinates);
        rideRepo.createRide(ride);
    }

    public Ride getRide(@NonNull String id) throws BadRequestException{
        return rideRepo.getRide(id);
    }

    public void endRide(@NonNull String id, Coordinates destCoordinates, int duration) 
                        throws BadRequestException, RideAlreadyEndedException{
        Ride ride = rideRepo.getRide(id);
        ride.end();
        ride.setDestinationCoordinates(destCoordinates);
        ride.setDuration(duration);
    }
}
