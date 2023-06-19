package ride.sharing.geektrust.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import ride.sharing.geektrust.facades.RideFacade;

@AllArgsConstructor
@Component
public class EndRideCommand implements ICommand{

    @Autowired
    private final RideFacade rideFacade;

    @Override
    public void execute(List<String> tokens) {
        try{
            String rideId = tokens.get(1);
            int destxc = Integer.valueOf(tokens.get(2));
            int destyc = Integer.valueOf(tokens.get(3));
            int duration = Integer.valueOf(tokens.get(4));
            rideFacade.endRide(rideId, destxc, destyc, duration);
            System.out.println("RIDE_STOPPED " + rideId);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
