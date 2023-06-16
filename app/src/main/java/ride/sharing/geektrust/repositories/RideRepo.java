package ride.sharing.geektrust.repositories;

import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ride.sharing.geektrust.entities.Ride;
import ride.sharing.geektrust.exceptions.BadRequestException;

@AllArgsConstructor
@Repository
public class RideRepo {
    
    private final Map<String, Ride> rideMap;

    public void createRide(Ride ride) throws BadRequestException{
        if(rideMap.containsKey(ride.getId())){
            throw new BadRequestException("Ride already exists!!");
        }
        rideMap.put(ride.getId(), ride);
    }

    public Ride getRide(@NonNull String id) throws BadRequestException{
        if(rideMap.containsKey(id)){
            return rideMap.get(id);
        }
        throw new BadRequestException("INVALID_RIDE");
    }

}
