package ride.sharing.geektrust.services;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ride.sharing.geektrust.constants.AppConstants;
import ride.sharing.geektrust.dto.Bill;
import ride.sharing.geektrust.entities.Ride;
import ride.sharing.geektrust.exceptions.BadRequestException;
import ride.sharing.geektrust.utils.DistanceUtil;

@AllArgsConstructor
public class BillingService {

    private final RideService rideService;
    
    public Bill createBill(@NonNull String rideId) throws BadRequestException{
        Ride ride = rideService.getRide(rideId);
        double billAmount = calculateBillAmount(ride);
        Bill bill = new Bill(rideId, ride.getDriver().getId(), billAmount);
        return bill;
    }

    private double calculateBillAmount(Ride ride){
        double distance = DistanceUtil.calculateDistance(ride.getSourceCoordinates(), 
                                                    ride.getDestinatioCoordinates());
        int duration = ride.getDuration();
        double amount = 0.0;
        amount += AppConstants.BASE_FARE;
        amount += AppConstants.PER_KM_FARE*distance;
        amount += AppConstants.PER_MINUTE_FARE*duration;
        amount += AppConstants.SALES_TAX*amount;
        return amount;
    }

}
