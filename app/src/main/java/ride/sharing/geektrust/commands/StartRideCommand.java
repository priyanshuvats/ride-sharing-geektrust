package ride.sharing.geektrust.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import ride.sharing.geektrust.facades.RideFacade;

@AllArgsConstructor
@Component
public class StartRideCommand implements ICommand{

    @Autowired
    private final RideFacade rideFacade;

    @Override
    public void execute(List<String> tokens) {
        try{
            String rideId = tokens.get(1);
            rideFacade.startRide(rideId, Integer.valueOf(tokens.get(2)), tokens.get(3));
            System.out.println("RIDE_STARTED " + rideId);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
