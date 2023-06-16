package ride.sharing.geektrust.constants;

import java.text.DecimalFormat;

public final class AppConstants {
    public static final DecimalFormat BILL_DECIMAL_FORMAT = new DecimalFormat("0.00");
    public static final int DRIVER_MATCH_DEFAULT_RADIUS = 5;
    public static final int MATCHED_DRIVER_COUNT_UPPER_LIMIT = 5;
    public static final int BASE_FARE = 50;
    public static final double PER_KM_FARE = 6.5;
    public static final int PER_MINUTE_FARE = 2;
    public static final double SALES_TAX = 0.2;
}
