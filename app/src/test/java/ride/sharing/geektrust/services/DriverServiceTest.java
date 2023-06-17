package ride.sharing.geektrust.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ride.sharing.geektrust.dto.Coordinates;
import ride.sharing.geektrust.entities.Driver;
import ride.sharing.geektrust.repositories.DriverRepo;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {
    
    @Mock
    DriverRepo driverRepoMock;

    @InjectMocks
    DriverService driverService;

    @Test
    public void getInRadiusDrivers_shouldReturnValidDrivers(){
        Coordinates riderCoordinates = new Coordinates(0, 0);
        List<Driver> validDrivers = new ArrayList<>(List.of(
            new Driver("D1", new Coordinates(3, 4)),
            new Driver("D2", new Coordinates(-2, 1)),
            new Driver("D3", new Coordinates(4, 4))
        ));
        when(driverRepoMock.getDrivers(
            new Coordinates(-5, -5),
            new Coordinates(5, 5), true))
        .thenReturn(validDrivers);

        List<Driver> expectedDrivers = new ArrayList<>(List.of(
            new Driver("D2", new Coordinates(-2, 1)),
            new Driver("D1", new Coordinates(3, 4))
        ));
        // less than limit
        List<Driver> actualDrivers = driverService.getInRadiusDrivers(riderCoordinates, 5, true, 5);
        Assertions.assertEquals(expectedDrivers, actualDrivers);
        // more than limit
        expectedDrivers.remove(expectedDrivers.size()-1);
        actualDrivers = driverService.getInRadiusDrivers(riderCoordinates, 5, true, 1);
        Assertions.assertEquals(expectedDrivers, actualDrivers);
    }

}
