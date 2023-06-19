package ride.sharing.geektrust.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import ride.sharing.geektrust.dto.Bill;
import ride.sharing.geektrust.services.BillingService;

@AllArgsConstructor
@Component
public class GenerateBillCommand implements ICommand{

    @Autowired
    private final BillingService billingService;

    @Override
    public void execute(List<String> tokens) {
        try{
            String rideId = tokens.get(1);
            Bill bill = billingService.createBill(rideId);
            System.out.println(bill.toString());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
