package ride.sharing.geektrust.matchStrategies;

import java.util.List;
import ride.sharing.geektrust.entities.Driver;

public interface IMatchStrategy {
    public List<Driver> matchDrivers(String riderId);
}
