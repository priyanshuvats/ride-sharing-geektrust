package ride.sharing.geektrust.matchStrategies;

import java.util.List;
import ride.sharing.geektrust.entities.Driver;
import ride.sharing.geektrust.exceptions.BadRequestException;

public interface IMatchStrategy {
    public List<Driver> matchDrivers(String riderId) throws BadRequestException;
}
