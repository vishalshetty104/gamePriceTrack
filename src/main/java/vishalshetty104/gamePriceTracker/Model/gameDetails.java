package vishalshetty104.gamePriceTracker.Model;

import jakarta.persistence.*;

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

    public Double getCurrentPrice(){
        return prices.get(0).formatNumber(prices.get(0).price_new);
    }

    public Integer getPriceCut(){
        return prices.get(0).price_cut;
    }
    public String getUrl(){
        return prices.get(0).url;
    }
    public String getSteamUrl(){
        for(int i=0;i<prices.size();i++){
            if(prices.get(i).url.contains("store.steampowered.com")){
                return prices.get(i).url;
            }
        }
        return "";
    }
    @Override
    public String toString() {
        return "gameDetails{" +
                "id=" + id +
                ", gameTitle='" + gameTitle + '\'' +
                ", plain='" + plain + '\'' +
                ", prices=" + prices.toString() +
                '}';
    }
}
