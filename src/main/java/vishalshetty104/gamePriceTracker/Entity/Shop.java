package vishalshetty104.gamePriceTracker.Entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Embeddable
public class Shop {

    public String id;
    public String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
