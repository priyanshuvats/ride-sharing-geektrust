package ride.sharing.geektrust.matchStrategies;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ride.sharing.geektrust.constants.AppConstants;
import ride.sharing.geektrust.dto.Coordinates;
import ride.sharing.geektrust.entities.Rider;
import ride.sharing.geektrust.exceptions.BadRequestException;
import ride.sharing.geektrust.services.DriverService;
import ride.sharing.geektrust.services.RiderService;

@ExtendWith(MockitoExtension.class)
public class DistanceStrategyTest {
    
    @Mock
    RiderService riderServiceMock;

    @Mock
    DriverService driverServiceMock;

    @InjectMocks
    DistanceStrategy distanceStrategy;

    @Test
    public void matchDrivers_shouldCallDriverServiceWithCorrectArgs() throws BadRequestException{
        Rider mockRider = new Rider("R1", new Coordinates(0, 0));
        when(riderServiceMock.getRider("R1")).thenReturn(mockRider);
        distanceStrategy.matchDrivers("R1");
        verify(driverServiceMock).getInRadiusDrivers(
                new Coordinates(0, 0), 
                AppConstants.DRIVER_MATCH_DEFAULT_RADIUS, 
                true, 
                AppConstants.MATCHED_DRIVER_COUNT_UPPER_LIMIT);
    }

}
