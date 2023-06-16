package ride.sharing.geektrust.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ride.sharing.geektrust.dto.Coordinates;

@Getter
@Setter
@AllArgsConstructor
public class Rider {
    private final String id;
    private Coordinates coordinates;
}
