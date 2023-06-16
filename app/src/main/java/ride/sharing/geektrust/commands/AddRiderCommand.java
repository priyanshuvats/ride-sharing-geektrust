package ride.sharing.geektrust.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import ride.sharing.geektrust.services.RiderService;

@AllArgsConstructor
@Component
public class AddRiderCommand implements ICommand{

    @Autowired
    private final RiderService riderService;

    @Override
    public void execute(List<String> tokens) {
        try{
            String driverId = tokens.get(1);
            int xc = Integer.valueOf(tokens.get(2));
            int yc = Integer.valueOf(tokens.get(3));
            riderService.addRider(driverId, xc, yc);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
