package vishalshetty104.gamePriceTracker.Model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.text.DecimalFormat;
import java.util.List;



@Embeddable
public class priceDetails {
    public Double price_new;
    public Double price_old;
    public int price_cut;
    public String url;
    @Embedded
    public Shop shop;

    public priceDetails() {
    }

    public priceDetails(Double price_new, Double price_old, int price_cut, String url, Shop shop, List<String> drm) {
        this.price_new = price_new;
        this.price_old = price_old;
        this.price_cut = price_cut;
        this.url = url;
        this.shop = shop;
        this.drm = drm;

    }
    public Double formatNumber(Double price){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return Double.valueOf(decimalFormat.format(price*52));
    }

    public Double getPrice_new() {
        return formatNumber(price_new);
    }

    public Double getPrice_old() {
        return formatNumber(price_old);
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
