package ride.sharing.geektrust.dto;

import java.text.DecimalFormat;

import lombok.AllArgsConstructor;
import ride.sharing.geektrust.constants.AppConstants;

@AllArgsConstructor
public class Bill {

    private String rideId;
    private String driverId;
    private double amount;
    
    @Override
    public String toString() {
        DecimalFormat df = AppConstants.BILL_DECIMAL_FORMAT;
        return "Bill " + rideId + " " + driverId + " " + df.format(amount);
    }
    
}
