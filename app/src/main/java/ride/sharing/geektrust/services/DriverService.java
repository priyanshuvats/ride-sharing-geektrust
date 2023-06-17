package ride.sharing.geektrust.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ride.sharing.geektrust.dto.Coordinates;
import ride.sharing.geektrust.entities.Driver;
import ride.sharing.geektrust.exceptions.BadRequestException;
import ride.sharing.geektrust.exceptions.DriverAlreadyBookedException;
import ride.sharing.geektrust.repositories.DriverRepo;
import ride.sharing.geektrust.utils.DistanceUtil;

@AllArgsConstructor
@Service
public class DriverService {

    @Autowired
    DriverRepo driverRepo;

    public void addDriver(@NonNull String id, int xc, int yc) throws BadRequestException{
        Driver driver = new Driver(id, new Coordinates(xc, yc));
        driverRepo.addDriver(driver);
    }

    public void bookDriver(@NonNull String id) throws DriverAlreadyBookedException, BadRequestException{
        Driver driver = driverRepo.getDriver(id);
        driver.book();
    }

    public void endBooking(@NonNull String id) throws BadRequestException{
        Driver driver = driverRepo.getDriver(id);
        driver.setBooked(false);
    }

    public List<Driver> getInRadiusDrivers(Coordinates c, int radius, 
                                            boolean excludeBooked, int limit){
        Coordinates leftBound = new Coordinates(c.getX()-radius, c.getY()-radius);
        Coordinates rightBound = new Coordinates(c.getX()+radius, c.getY()+radius);
        List<Driver> drivers = driverRepo.getDrivers(leftBound, rightBound, excludeBooked);
        List<Driver> inRadiusDrivers = new ArrayList<>();
        int i=0;
        while(i<drivers.size()){
            Driver driver = drivers.get(i);
            double dist = DistanceUtil.calculateDistance(c, driver.getCoordinates());
            if(dist<=radius){inRadiusDrivers.add(driver);}
            i++;
        }
        Collections.sort(inRadiusDrivers, new DistanceComparator(c));
        return inRadiusDrivers.subList(0, Math.min(limit, inRadiusDrivers.size()));
    }

    @AllArgsConstructor
    private class DistanceComparator implements Comparator<Driver>{
        private Coordinates c;
        public int compare(Driver d1, Driver d2){
            return (int)(DistanceUtil.calculateDistance(c, d1.getCoordinates()) - 
                    DistanceUtil.calculateDistance(c, d2.getCoordinates()));
        }
    }
}
