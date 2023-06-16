package ride.sharing.geektrust.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ride.sharing.geektrust.entities.Driver;
import ride.sharing.geektrust.exceptions.BadRequestException;
import ride.sharing.geektrust.matchStrategies.IMatchStrategy;
import ride.sharing.geektrust.repositories.DriverMatchRepo;

@AllArgsConstructor
@Service
public class MatchService {
    @Autowired
    private final IMatchStrategy matchStrategy;
    @Autowired
    private final DriverMatchRepo driverMatchRepo;
    
    public List<Driver> matchDrivers(@NonNull String riderId) throws BadRequestException{
        List<Driver> matchedDrivers = matchStrategy.matchDrivers(riderId);
        driverMatchRepo.addMatch(riderId, matchedDrivers);
        return matchedDrivers;
    }

    public List<Driver> getMatchedDrivers(@NonNull String riderId){
        return driverMatchRepo.getMatchedDrivers(riderId);
    }

}
