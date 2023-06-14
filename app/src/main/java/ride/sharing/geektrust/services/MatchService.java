package ride.sharing.geektrust.services;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ride.sharing.geektrust.entities.Driver;
import ride.sharing.geektrust.matchStrategies.IMatchStrategy;
import ride.sharing.geektrust.repositories.DriverMatchRepo;

@AllArgsConstructor
public class MatchService {

    private final IMatchStrategy matchStrategy;
    private final DriverMatchRepo driverMatchRepo;
    
    public List<Driver> matchDrivers(@NonNull String riderId){
        List<Driver> matchedDrivers = matchStrategy.matchDrivers(riderId);
        driverMatchRepo.addMatch(riderId, matchedDrivers);
        return matchedDrivers;
    }

    public List<Driver> getMatchedDrivers(@NonNull String riderId){
        return driverMatchRepo.getMatchedDrivers(riderId);
    }

}
