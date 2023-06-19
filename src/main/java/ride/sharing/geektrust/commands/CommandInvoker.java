package ride.sharing.geektrust.commands;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import ride.sharing.geektrust.exceptions.BadRequestException;

@AllArgsConstructor
public class CommandInvoker {
    private final Map<String, ICommand> commandMap;

    public void executeCommand(String commandName, List<String> tokens) throws BadRequestException{
        if(!commandMap.containsKey(commandName)){
            throw new BadRequestException("Such a command doesn't exist!!");
        }
        ICommand command = commandMap.get(commandName);
        command.execute(tokens);
    }

    public void addCommand(String commandName, ICommand command){
        commandMap.put(commandName, command);
    }
}
