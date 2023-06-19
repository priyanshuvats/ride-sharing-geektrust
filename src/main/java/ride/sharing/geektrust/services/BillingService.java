package ride.sharing.geektrust.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ride.sharing.geektrust.constants.AppConstants;
import ride.sharing.geektrust.dto.Bill;
import ride.sharing.geektrust.entities.Ride;
import ride.sharing.geektrust.exceptions.BadRequestException;
import ride.sharing.geektrust.utils.DistanceUtil;

@AllArgsConstructor
@Service
public class BillingService {
    @Autowired
    private final RideService rideService;
    
    public Bill createBill(@NonNull String rideId) throws BadRequestException{
        Ride ride = rideService.getRide(rideId);
        if(!ride.isHasEnded()){
            throw new BadRequestException("RIDE_NOT_COMPLETED");
        }
        double billAmount = calculateBillAmount(ride);
        Bill bill = new Bill(rideId, ride.getDriver().getId(), billAmount);
        return bill;
    }

    private double calculateBillAmount(Ride ride){
        double distance = DistanceUtil.calculateDistance(ride.getSourceCoordinates(), 
                                                    ride.getDestinationCoordinates());
        int duration = ride.getDuration();
        double amount = 0.0;
        amount += AppConstants.BASE_FARE;
        amount += AppConstants.PER_KM_FARE*distance;
        amount += AppConstants.PER_MINUTE_FARE*duration;
        amount += AppConstants.SALES_TAX*amount;
        return amount;
    }

}
