package vishalshetty104.gamePriceTracker.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vishalshetty104.gamePriceTracker.Entity.gameDetails;
import vishalshetty104.gamePriceTracker.Service.gameAPIClient;
import vishalshetty104.gamePriceTracker.Service.gameDbService;

import java.util.List;

@RestController
public class webController {

    @Autowired
    gameAPIClient gameAPIClient;
    @Autowired
    gameDbService gameService;

    @GetMapping("/")
    public ModelAndView index() throws JsonProcessingException {
        ModelAndView mv = new ModelAndView("index");
        List<gameDetails> list = gameAPIClient.dealsList();
        mv.addObject("games",list);
        return mv;
    }
    @GetMapping("/test")
    public String test(){
        return "API started!";
    }
    @GetMapping("/search")
    public ModelAndView searchGame(@RequestParam(value = "gameName") String name) throws JsonProcessingException {
        //String name = "cyberpunk 2077";
        ModelAndView mv = new ModelAndView("search");
        gameDetails game = gameAPIClient.getGameDetails(name);
        mv.addObject("url",game.getUrl());
        mv.addObject("game",game);
        return mv;

    }
    @PostMapping("/search/save")
    public ResponseEntity<String> searchGame(@RequestBody gameDetails game){
        try {
            gameService.saveGameDetails(game);
            return new ResponseEntity<>("game saved successfully", HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("error saving game details",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/myGames")
    public ModelAndView myGamesList(){
        ModelAndView mv = new ModelAndView("myGames");
        List<gameDetails> gameList = gameService.getGames();
        mv.addObject("games",gameList);
        return mv;
    }

}
