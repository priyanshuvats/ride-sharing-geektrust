package ride.sharing.geektrust.facades;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ride.sharing.geektrust.dto.Coordinates;
import ride.sharing.geektrust.entities.Driver;
import ride.sharing.geektrust.entities.Ride;
import ride.sharing.geektrust.entities.Rider;
import ride.sharing.geektrust.exceptions.BadRequestException;
import ride.sharing.geektrust.exceptions.DriverAlreadyBookedException;
import ride.sharing.geektrust.exceptions.InvalidRideException;
import ride.sharing.geektrust.exceptions.RideAlreadyEndedException;
import ride.sharing.geektrust.services.DriverService;
import ride.sharing.geektrust.services.MatchService;
import ride.sharing.geektrust.services.RideService;
import ride.sharing.geektrust.services.RiderService;

@ExtendWith(MockitoExtension.class)
public class RideFacadeTest {

    @Mock
    DriverService driverServiceMock;
    @Mock
    RiderService riderServiceMock;
    @Mock
    MatchService matchServiceMock;
    @Mock
    RideService rideServiceMock;

    @InjectMocks
    RideFacade rideFacade;

    Rider riderStub;
    List<Driver> matchedDriversStub;

    @BeforeEach
    public void setup() throws BadRequestException{
        riderStub = new Rider("R1", new Coordinates(0, 0));
        matchedDriversStub = new ArrayList<>(List.of(
                new Driver("D1", new Coordinates(1, 2)),
                new Driver("D2", new Coordinates(-1, -3))
            )
        );
    }

    @Test
    public void startRide_shouldCreateValidRide() throws BadRequestException, InvalidRideException, DriverAlreadyBookedException{
        String rideId = "RIDE1";

        when(riderServiceMock.getRider("R1"))
        .thenReturn(riderStub);
        when(matchServiceMock.getMatchedDrivers("R1"))
        .thenReturn(matchedDriversStub);

        rideFacade.startRide(rideId, 1, "R1");

        verify(driverServiceMock).bookDriver("D1");
        verify(rideServiceMock)
        .createRide(rideId, matchedDriversStub.get(0), 
                riderStub, new Coordinates(0, 0));
    }

    @Test
    public void startRide_invalidDriverIdx_shouldThrowError() throws BadRequestException, InvalidRideException, DriverAlreadyBookedException{
        String rideId = "RIDE1";
        when(matchServiceMock.getMatchedDrivers("R1"))
        .thenReturn(matchedDriversStub);
        
        Assertions.assertThrows(InvalidRideException.class, 
        ()->{
            rideFacade.startRide(rideId, 3, "R1");
        });
    }

    @Test
    public void startRide_bookedDriver_shouldThrowError() throws BadRequestException, InvalidRideException, DriverAlreadyBookedException{
        String rideId = "RIDE1";
        Driver driver = matchedDriversStub.get(0);
        driver.book();
        when(matchServiceMock.getMatchedDrivers("R1"))
        .thenReturn(matchedDriversStub);

        Assertions.assertThrows(InvalidRideException.class, 
        ()->{
            rideFacade.startRide(rideId, 1, "R1");
        });

        driver.setBooked(false);
    }

    @Test
    public void startRide_existingRide_shouldThrowError() throws BadRequestException, InvalidRideException, DriverAlreadyBookedException{
        String rideId = "RIDE1";
        when(riderServiceMock.getRider("R1"))
        .thenReturn(riderStub);
        when(matchServiceMock.getMatchedDrivers("R1"))
        .thenReturn(matchedDriversStub);

        doThrow(new BadRequestException("Test Error"))
        .when(rideServiceMock).createRide(eq("RIDE1"), any(), any(), any());

        Assertions.assertThrows(InvalidRideException.class, 
        ()->{
            rideFacade.startRide(rideId, 1, "R1");
        });
    }

    @Test
    public void endRide_validParams_shouldEndRide() throws BadRequestException, 
        RideAlreadyEndedException, InvalidRideException
    {
        String rideId = "RIDE1";
        Driver driverStub = matchedDriversStub.get(0);
        Ride rideStub = new Ride(rideId, riderStub, driverStub, 
                        riderStub.getCoordinates());
        when(rideServiceMock.getRide(rideId))
        .thenReturn(rideStub);

        rideFacade.endRide(rideId, 77, 6, 20);

        verify(driverServiceMock).endBooking(driverStub.getId());
        verify(rideServiceMock)
        .endRide(rideId, new Coordinates(77, 6), 20);
    }

    @Test
    public void endRide_invalidRide_shouldThrowError() throws BadRequestException{
        doThrow(new BadRequestException("TEST"))
        .when(rideServiceMock).getRide(eq("RIDE1"));
        
        Assertions.assertThrows(InvalidRideException.class, 
        ()->{
            rideFacade.endRide("RIDE1", 0, 0, 10);
        });
    }

    @Test
    public void endRide_alreadyEnded_shouldThrowError() throws BadRequestException{
        String rideId = "RIDE1";
        Driver driverStub = matchedDriversStub.get(0);
        Ride rideStub = new Ride(rideId, riderStub, driverStub, 
                        riderStub.getCoordinates());
        
        when(rideServiceMock.getRide(rideId))
        .thenReturn(rideStub);
        doThrow(new BadRequestException("TEST"))
        .when(driverServiceMock).endBooking(eq("D1"));
        
        Assertions.assertThrows(InvalidRideException.class, 
        ()->{
            rideFacade.endRide("RIDE1", 0, 0, 10);
        });
    }

}
