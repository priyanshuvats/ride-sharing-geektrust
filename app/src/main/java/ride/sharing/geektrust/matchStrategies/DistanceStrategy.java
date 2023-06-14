package ride.sharing.geektrust.matchStrategies;

import java.util.List;

import lombok.AllArgsConstructor;
import ride.sharing.geektrust.constants.AppConstants;
import ride.sharing.geektrust.entities.Driver;
import ride.sharing.geektrust.entities.Rider;
import ride.sharing.geektrust.exceptions.BadRequestException;
import ride.sharing.geektrust.services.DriverService;
import ride.sharing.geektrust.services.RiderService;

@AllArgsConstructor
public class DistanceStrategy implements IMatchStrategy{

    RiderService riderService;
    DriverService driverService;

    @Override
    public List<Driver> matchDrivers(String riderId) throws BadRequestException {
        Rider rider = riderService.getRider(riderId);
        int radius = AppConstants.DRIVER_MATCH_DEFAULT_RADIUS;
        int limit = AppConstants.MATCHED_DRIVER_COUNT_UPPER_LIMIT;
        List<Driver> matchedDrivers = 
        driverService.getInRadiusDrivers(rider.getCoordinates(), radius, false, limit);
        return matchedDrivers;
    }
    
}
