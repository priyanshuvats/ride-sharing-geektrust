package ride.sharing.geektrust.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ride.sharing.geektrust.dto.Coordinates;
import ride.sharing.geektrust.entities.Rider;
import ride.sharing.geektrust.exceptions.BadRequestException;
import ride.sharing.geektrust.repositories.RiderRepo;

@Service
@AllArgsConstructor
public class RiderService {
    @Autowired
    RiderRepo riderRepo;

    public Rider getRider(@NonNull String id) throws BadRequestException{
        return riderRepo.getRider(id);
    }

    public void addRider(String id, int xc, int yc) throws BadRequestException{
        Rider rider = new Rider(id, new Coordinates(xc, yc));
        riderRepo.addRider(rider);
    }
}
