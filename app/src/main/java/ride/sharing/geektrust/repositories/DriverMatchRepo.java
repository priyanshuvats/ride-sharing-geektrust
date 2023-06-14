package ride.sharing.geektrust.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ride.sharing.geektrust.entities.Driver;

@AllArgsConstructor
public class DriverMatchRepo {
    
    private final Map<String, List<Driver>> riderDriversMatch;

    public List<Driver> getMatchedDrivers(@NonNull String riderId){
        return riderDriversMatch.getOrDefault(riderId, new ArrayList<>());
    }

    public void addMatch(@NonNull String riderId, @NonNull List<Driver> drivers){
        riderDriversMatch.put(riderId, drivers);
    }
}
