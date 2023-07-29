package vishalshetty104.gamePriceTracker.Entity;

import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.util.List;


@Embeddable
public class priceDetails {
    public Double price_new;
    public Double price_old;
    public int price_cut;
    public String url;
    @Embedded
    public Shop shop;

    public Double getPrice_new() {
        return price_new;
    }

    public Double getPrice_old() {
        return price_old;
    }

    public int getPrice_cut() {
        return price_cut;
    }

    public String getUrl() {
        return url;
    }

    public String getShop() {
        return shop.id;
    }

    public List<String> drm;

}
