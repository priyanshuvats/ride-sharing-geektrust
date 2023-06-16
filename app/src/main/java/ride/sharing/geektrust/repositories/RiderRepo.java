package ride.sharing.geektrust.repositories;

import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ride.sharing.geektrust.entities.Rider;
import ride.sharing.geektrust.exceptions.BadRequestException;

@AllArgsConstructor
@Repository
public class RiderRepo {
    
    private final Map<String, Rider> riderMap;

    public Rider getRider(@NonNull String id) throws BadRequestException{
        if(riderMap.containsKey(id)){
            return riderMap.get(id);
        }
        throw new BadRequestException("Rider doesn't exists!!");
    }

    public void addRider(@NonNull Rider rider) throws BadRequestException{
        if(riderMap.containsKey(rider.getId())){
            throw new BadRequestException("Rider Already Exists!!");
        }
        riderMap.put(rider.getId(), rider);
    }

}
