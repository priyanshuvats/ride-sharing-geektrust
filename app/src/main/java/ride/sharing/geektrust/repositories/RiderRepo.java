package ride.sharing.geektrust.repositories;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ride.sharing.geektrust.entities.Rider;

@AllArgsConstructor
public class RiderRepo {
    
    Map<String, Rider> riderMap;

    public Rider getRider(@NonNull String id){
        if(riderMap.containsKey(id)){
            return riderMap.get(id);
        }
        throw new Error("Rider doesn't exists!!");
    }

    public void addRider(@NonNull Rider rider){
        if(riderMap.containsKey(rider.getId())){
            throw new Error("Rider Already Exists!!");
        }
        riderMap.put(rider.getId(), rider);
    }

}
