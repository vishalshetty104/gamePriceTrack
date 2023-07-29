package vishalshetty104.gamePriceTracker.Entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@Entity
public class gameDetails {
    @Id
    private Long id;
    @Column
    private String gameTitle;
    @Column
    private String plain;
    @ElementCollection
    @CollectionTable(name = "price_details")
    private List<priceDetails> prices;

    public gameDetails(){}

    public gameDetails(Long id, String gameTitle, String plain, List<priceDetails> prices) {
        this.id = id;
        this.gameTitle = gameTitle;
        this.plain = plain;
        this.prices = prices;
    }

    public Long getId() {
        return id;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public String getPlain() {
        return plain;
    }

    public void setPrices(ArrayList<priceDetails> prices) {
        this.prices = prices;
    }

    public List<priceDetails> getPrices() {
        return prices;
    }

    @Override
    public String toString() {
        return "gameDetails{" +
                "id=" + id +
                ", gameTitle='" + gameTitle + '\'' +
                ", plain='" + plain + '\'' +
                ", prices=" + prices.get(0).getPrice_new() +
                '}';
    }
}
