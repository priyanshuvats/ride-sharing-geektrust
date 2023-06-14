package ride.sharing.geektrust.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RideAlreadyEndedException extends Exception {
    private String message;

    public String toString(){
        return message;
    }
}
