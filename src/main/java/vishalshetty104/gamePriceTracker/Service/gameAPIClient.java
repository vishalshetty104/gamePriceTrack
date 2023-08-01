package vishalshetty104.gamePriceTracker.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vishalshetty104.gamePriceTracker.Entity.Shop;
import vishalshetty104.gamePriceTracker.Entity.gameDetails;
import vishalshetty104.gamePriceTracker.Entity.priceDetails;
import vishalshetty104.gamePriceTracker.Repository.gameRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class gameAPIClient {
    @Autowired
    gameRepository repository;
    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper om = new ObjectMapper();
    public gameDetails getGameDetails(String name) throws JsonProcessingException {
        String plainUrl = "https://api.isthereanydeal.com/v02/search/search/?key=665d5c5e9bbe2ddf928bcd5ad2ac14cb7ab53b75&q="+name+"&limit=1"; //get plain,id and title of game
        String plainData = restTemplate.getForObject(plainUrl,String.class);
        JsonNode plainNode = om.readTree(plainData);
        JsonNode details = plainNode.get("data").get("results").get(0);
        Long id = details.get("id").asLong();
        String plain = details.get("plain").asText();
        String title = details.get("title").asText();
        String priceUrl = "https://api.isthereanydeal.com/v01/game/prices/?key=665d5c5e9bbe2ddf928bcd5ad2ac14cb7ab53b75&plains="+plain+"&region=us&country=IN&shops=steam,gog&exclude=voidu%2Citchio&added=0";
        String priceData = restTemplate.getForObject(priceUrl,String.class);

        JsonNode priceNode = om.readTree(priceData);
        JsonNode list = priceNode.get("data").get(plain).get("list");
        String listJson = om.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        TypeReference<List<priceDetails>> typeReference = new TypeReference<List<priceDetails>>() {};
        List<priceDetails> res = om.readValue(listJson, typeReference);
        gameDetails game = new gameDetails(id,title,plain,res);

        return game;
    }
    public List<gameDetails> dealsList() throws JsonProcessingException {
        List<gameDetails> gameList = new ArrayList<>();
        String url = "https://api.isthereanydeal.com/v01/deals/list/?key=665d5c5e9bbe2ddf928bcd5ad2ac14cb7ab53b75";
        String responseData = restTemplate.getForObject(url, String.class);
        JsonNode data = om.readTree(responseData);
        String plain, title;
        priceDetails pd;
        Shop shop;
        gameDetails tempGame;

        TypeReference<List<String>> listTypeReference = new TypeReference<List<String>>() {};
        for(int i=0;i<=10;i++){
            JsonNode details = data.get("data").get("list").get(i);
            plain = details.get("plain").asText();
            title = details.get("title").asText();
            List<String> drm = om.readValue(om.writerWithDefaultPrettyPrinter().writeValueAsString(details.get("drm")), listTypeReference);
            //shop = om.readValue(om.writerWithDefaultPrettyPrinter().writeValueAsString(details.get("shop")), Shop.class);
            shop = new Shop(details.get("shop").get("id").asText(),details.get("shop").get("name").asText());
            pd = new priceDetails(details.get("price_new").asDouble(),details.get("price_old").asDouble(),details.get("price_cut").asInt(),details.get("urls").get("buy").asText(),shop,drm);
            List<priceDetails> pdList = new ArrayList<>();
            pdList.add(pd);
            tempGame = new gameDetails((long) i,title,plain,pdList);
            gameList.add(tempGame);
        }
        return gameList;
    }
}
