package ride.sharing.geektrust.services;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import ride.sharing.geektrust.entities.Driver;
import ride.sharing.geektrust.matchStrategies.IMatchStrategy;

@AllArgsConstructor
public class MatchService {

    private final IMatchStrategy matchStrategy;
    
    public List<Driver> matchDrivers(String riderId){
        return new ArrayList<Driver>();
    }

}
