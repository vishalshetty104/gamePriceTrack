package vishalshetty104.gamePriceTracker.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vishalshetty104.gamePriceTracker.Service.gameAPIClient;

@RestController
public class webController {

    @Autowired
    gameAPIClient gameAPIClient;
    @GetMapping("/home")
    public String home(){
        return "API started!";
    }
    @GetMapping("/search")
    public String getRDR2() throws JsonProcessingException {
        String name = "cyberpunk 2077";
        return gameAPIClient.getGameDetails(name);

    }
}
