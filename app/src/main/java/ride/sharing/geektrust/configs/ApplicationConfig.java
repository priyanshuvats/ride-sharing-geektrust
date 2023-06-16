package ride.sharing.geektrust.configs;

import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import ride.sharing.geektrust.commands.AddDriverCommand;
import ride.sharing.geektrust.commands.AddRiderCommand;
import ride.sharing.geektrust.commands.CommandInvoker;
import ride.sharing.geektrust.commands.EndRideCommand;
import ride.sharing.geektrust.commands.GenerateBillCommand;
import ride.sharing.geektrust.commands.MatchCommand;
import ride.sharing.geektrust.commands.StartRideCommand;
import ride.sharing.geektrust.repositories.DriverMatchRepo;
import ride.sharing.geektrust.repositories.DriverRepo;
import ride.sharing.geektrust.repositories.RideRepo;
import ride.sharing.geektrust.repositories.RiderRepo;

@Getter
@Configuration
@ComponentScan("ride.sharing.geektrust")
public class ApplicationConfig {

    private final CommandInvoker commandInvoker = new CommandInvoker(new HashMap<>());
    private final DriverRepo driverRepo = new DriverRepo(new HashMap<>());
    private final RiderRepo riderRepo = new RiderRepo(new HashMap<>());
    private final DriverMatchRepo driverMatchRepo = new DriverMatchRepo(new HashMap<>());
    private final RideRepo rideRepo = new RideRepo(new HashMap<>());

    public void addCommands(ApplicationContext context){
        commandInvoker.addCommand("ADD_DRIVER", context.getBean(AddDriverCommand.class));
        commandInvoker.addCommand("ADD_RIDER", context.getBean(AddRiderCommand.class));
        commandInvoker.addCommand("MATCH", context.getBean(MatchCommand.class));
        commandInvoker.addCommand("START_RIDE", context.getBean(StartRideCommand.class));
        commandInvoker.addCommand("STOP_RIDE", context.getBean(EndRideCommand.class));
        commandInvoker.addCommand("BILL", context.getBean(GenerateBillCommand.class));
    }

}
