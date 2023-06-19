package ride.sharing.geektrust.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ride.sharing.geektrust.entities.Driver;
import ride.sharing.geektrust.services.MatchService;

@AllArgsConstructor
@Component
public class MatchCommand implements ICommand{

    @Autowired
    private final MatchService matchService;

    @Override
    public void execute(List<String> tokens) {
        try{
            List<Driver> drivers = matchService.matchDrivers(tokens.get(1));
            showDrivers(drivers);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void showDrivers(@NonNull List<Driver> drivers){
        if(drivers.size()==0){
            System.out.println("NO_DRIVERS_AVAILABLE");
            return;
        }
        System.out.print("DRIVERS_MATCHED");
        for(Driver driver: drivers){
            System.out.print(" " + driver.getId());
        }
        System.out.println();
    }
    
}
