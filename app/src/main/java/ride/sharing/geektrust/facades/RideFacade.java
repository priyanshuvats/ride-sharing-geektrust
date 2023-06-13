package ride.sharing.geektrust.facades;

public class RideFacade {
    public void startRide(String rideId, int driverIndex, String riderId){
        // getDriver - check if available else throw error
        // update driver booking status - 2 threads might want to update, handle concurrency
        // createRide with driver, rider and source co-ordinates
    }

    public void endRide(String rideId, int destXC, int destYC, int duration){
        // get Ride
        // update ride with dest co-ordinates and duration
        // update driver booking status
    }
}
