package ride.sharing.geektrust.services;

import lombok.NonNull;
import ride.sharing.geektrust.dto.Coordinates;
import ride.sharing.geektrust.entities.Rider;
import ride.sharing.geektrust.exceptions.BadRequestException;
import ride.sharing.geektrust.repositories.RiderRepo;

public class RiderService {

    RiderRepo riderRepo;

    public Rider getRider(@NonNull String id) throws BadRequestException{
        return riderRepo.getRider(id);
    }

    public void addRider(String id, int xc, int yc) throws BadRequestException{
        Rider rider = new Rider(id, new Coordinates(xc, yc));
        riderRepo.addRider(rider);
    }
}
