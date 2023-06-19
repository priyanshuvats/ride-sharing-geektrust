package ride.sharing.geektrust.services;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ride.sharing.geektrust.dto.Bill;
import ride.sharing.geektrust.dto.Coordinates;
import ride.sharing.geektrust.entities.Driver;
import ride.sharing.geektrust.entities.Ride;
import ride.sharing.geektrust.entities.Rider;
import ride.sharing.geektrust.exceptions.BadRequestException;
import ride.sharing.geektrust.exceptions.RideAlreadyEndedException;

@ExtendWith(MockitoExtension.class)
public class BillingServiceTest {
    
    @Mock
    RideService rideServiceMock;

    @InjectMocks
    BillingService billingService;

    Ride rideStub;

    @BeforeEach
    public void setup() throws RideAlreadyEndedException{
        Rider rider = new Rider("R1", new Coordinates(3, 5));
        Driver driver = new Driver("D1", new Coordinates(2, 3));
        rideStub = new Ride("RIDE1", rider, driver, new Coordinates(3, 5));
        rideStub.setDestinationCoordinates(new Coordinates(10, 2));
        rideStub.setDuration(48);
        rideStub.end();
    }

    @Test
    public void createBill_rideEnded_shouldCreateValidBill() throws BadRequestException{
        when(rideServiceMock.getRide("RIDE1"))
        .thenReturn(rideStub);
        Bill expectedBill = new Bill("RIDE1", "D1", 234.64);
        Bill actualBill = billingService.createBill("RIDE1");
        Assertions.assertEquals(expectedBill.toString(), actualBill.toString());
    }

    @Test
    public void createBill_rideNotEnded_shouldThrowError() throws BadRequestException{
        rideStub.setHasEnded(false);
        when(rideServiceMock.getRide("RIDE1"))
        .thenReturn(rideStub);
        Assertions.assertThrows(BadRequestException.class, 
        ()->{
            billingService.createBill("RIDE1");
        });
    }

}
