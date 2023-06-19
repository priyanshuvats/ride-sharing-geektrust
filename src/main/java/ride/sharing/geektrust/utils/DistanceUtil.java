package ride.sharing.geektrust.utils;

import java.text.DecimalFormat;

import lombok.NonNull;
import ride.sharing.geektrust.constants.AppConstants;
import ride.sharing.geektrust.dto.Coordinates;

public class DistanceUtil {
    public static double calculateDistance(@NonNull Coordinates c1, @NonNull Coordinates c2){
        double dist = Math.sqrt(Math.pow(c2.getX()-c1.getX(), 2) + 
                        Math.pow(c2.getY()-c1.getY(), 2));
        DecimalFormat df = AppConstants.BILL_DECIMAL_FORMAT;
        return Double.valueOf(df.format(dist));
    }

    public static boolean insideBound(@NonNull Coordinates c, 
            @NonNull Coordinates leftBound, @NonNull Coordinates rightBound){
        int cx = c.getX();
        int cy = c.getY();
        if(cx<leftBound.getX() || cy<leftBound.getY() || 
            cx>rightBound.getX() || cy>rightBound.getY()){
            return false;
        }
        return true;
    }
}
