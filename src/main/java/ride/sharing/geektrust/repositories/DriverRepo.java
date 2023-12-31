package ride.sharing.geektrust.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ride.sharing.geektrust.dto.Coordinates;
import ride.sharing.geektrust.entities.Driver;
import ride.sharing.geektrust.exceptions.BadRequestException;
import ride.sharing.geektrust.utils.DistanceUtil;

@AllArgsConstructor
@Repository
public class DriverRepo {
    
    private final Map<String, Driver> driverMap;
   
    public Driver getDriver(@NonNull String id) throws BadRequestException{
        if(driverMap.containsKey(id)){
            return driverMap.get(id);
        }
        throw new BadRequestException("Driver doesn't exist!!");
    }

    public List<Driver> getDrivers(Coordinates leftBound, Coordinates rightBound,
                                    boolean excludeBooked){
        List<Driver> drivers = new ArrayList<>();
        for(String id: driverMap.keySet()){
            Driver driver = driverMap.get(id);
            if(DistanceUtil.insideBound(driver.getCoordinates(), leftBound, rightBound)){
                if(!excludeBooked || (excludeBooked && !driver.isBooked())){
                    drivers.add(driver);
                }
            }
        }
        return drivers;
    }

    public void addDriver(@NonNull Driver driver) throws BadRequestException{
        if(driverMap.containsKey(driver.getId())){
            throw new BadRequestException("Driver Already Exists!!");
        }
        driverMap.put(driver.getId(), driver);
    }

}
