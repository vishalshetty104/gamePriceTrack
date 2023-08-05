package vishalshetty104.gamePriceTracker.Model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Shop {

    public String id;
    public String name;

    public Shop() {
    }

    public Shop(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
