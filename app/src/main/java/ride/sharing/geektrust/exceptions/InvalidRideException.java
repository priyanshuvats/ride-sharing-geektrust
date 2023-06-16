package ride.sharing.geektrust.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidRideException extends Exception{

    private String Message;

    public String toString(){
        return Message;
    }
}
