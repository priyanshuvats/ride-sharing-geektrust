package ride.sharing.geektrust.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ride.sharing.geektrust.dto.Coordinates;
import ride.sharing.geektrust.entities.Driver;
import ride.sharing.geektrust.exceptions.BadRequestException;
import ride.sharing.geektrust.exceptions.DriverAlreadyBookedException;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class DriverRepoTest {

    private DriverRepo driverRepo;

    @BeforeAll
    public void setup() throws BadRequestException, DriverAlreadyBookedException{
        driverRepo = new DriverRepo(new HashMap<>());
        // inside
        driverRepo.addDriver(new Driver("D1", new Coordinates(1, 2)));
        // boundary booked
        Driver bookedBoundaryDriver = new Driver("D2", new Coordinates(3, 0));
        bookedBoundaryDriver.book();
        driverRepo.addDriver(bookedBoundaryDriver);
        // boundary
        driverRepo.addDriver(new Driver("D3", new Coordinates(-3, -3)));
        // right breach
        driverRepo.addDriver(new Driver("D4", new Coordinates(4, 1)));
        // top breach
        driverRepo.addDriver(new Driver("D5", new Coordinates(-2, 5)));
        // left breach
        driverRepo.addDriver(new Driver("D6", new Coordinates(-4, 1)));
        // bottom breach
        driverRepo.addDriver(new Driver("D7", new Coordinates(-3, -5)));
    }
    
    @Test
    public void getDrivers_includeBooked_shouldReturnValidDrivers() throws BadRequestException{
        List<Driver> expected = new ArrayList<>(List.of(
                driverRepo.getDriver("D1"),
                driverRepo.getDriver("D2"),
                driverRepo.getDriver("D3")
        ));
        List<Driver> actual = driverRepo.getDrivers(new Coordinates(-3, -3), new Coordinates(3, 3), false);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getDrivers_excludeBooked_shouldReturnValidDrivers() throws BadRequestException{
        List<Driver> expected = new ArrayList<>(List.of(
                driverRepo.getDriver("D1"),
                driverRepo.getDriver("D3")
        ));
        List<Driver> actual = driverRepo.getDrivers(new Coordinates(-3, -3), new Coordinates(3, 3), true);
        Assertions.assertEquals(expected, actual);
    }
}
