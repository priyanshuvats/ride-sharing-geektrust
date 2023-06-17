package ride.sharing.geektrust.facades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import ride.sharing.geektrust.dto.Coordinates;
import ride.sharing.geektrust.entities.Driver;
import ride.sharing.geektrust.entities.Ride;
import ride.sharing.geektrust.entities.Rider;
import ride.sharing.geektrust.exceptions.InvalidRideException;
import ride.sharing.geektrust.services.DriverService;
import ride.sharing.geektrust.services.MatchService;
import ride.sharing.geektrust.services.RideService;
import ride.sharing.geektrust.services.RiderService;

@AllArgsConstructor
@Component
public class RideFacade {
    @Autowired
    private final MatchService matchService;
    @Autowired
    private final RideService rideService;
    @Autowired
    private final RiderService riderService;
    @Autowired
    private final DriverService driverService;

    public void startRide(String rideId, int driverIndex, String riderId) throws InvalidRideException{
        List<Driver> matchedDrivers = matchService.getMatchedDrivers(riderId);
        if(matchedDrivers.size() < driverIndex || matchedDrivers.get(driverIndex-1).isBooked()){
            throw new InvalidRideException("INVALID_RIDE");
        }
        Driver driver = matchedDrivers.get(driverIndex-1);
        try {
            Rider rider = riderService.getRider(riderId);
            driverService.bookDriver(driver.getId());
            Coordinates sourceCoordinates = rider.getCoordinates().clone();
            rideService.createRide(rideId, driver, rider, sourceCoordinates);
        } catch (Exception e) {
            throw new InvalidRideException("INVALID_RIDE");
        }
    }

    public void endRide(String rideId, int destXC, int destYC, int duration) 
                        throws InvalidRideException{
        try {
            Ride ride = rideService.getRide(rideId);
            driverService.endBooking(ride.getDriver().getId());
            rideService.endRide(rideId, new Coordinates(destXC, destYC), duration);
        } catch (Exception e) {
            throw new InvalidRideException("INVALID_RIDE");
        }
    }
}
