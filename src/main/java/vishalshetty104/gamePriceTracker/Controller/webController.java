package vishalshetty104.gamePriceTracker.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vishalshetty104.gamePriceTracker.Controller.dto.userDTO;
import vishalshetty104.gamePriceTracker.Model.User;
import vishalshetty104.gamePriceTracker.Model.gameDetails;
import vishalshetty104.gamePriceTracker.Service.UserService;
import vishalshetty104.gamePriceTracker.Service.gameAPIService;
import vishalshetty104.gamePriceTracker.Service.gameDbService;


import java.io.IOException;
import java.util.List;

@RestController
public class webController {

//    @Autowired
//    userServiceImpl userServiceImpl;
    @Autowired
    UserService userService;
    @Autowired
    gameAPIService gameAPIService;
    @Autowired
    gameDbService gameService;

    @GetMapping("/")
    public ModelAndView index() throws JsonProcessingException {
        ModelAndView mv = new ModelAndView("index");
        List<gameDetails> list = gameAPIService.dealsList();
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
        gameDetails game = gameAPIService.getGameDetails(name);
        mv.addObject("prices",game.getPrices());
        mv.addObject("url", gameAPIService.generateSteamPicUrl(game));
        mv.addObject("game",game);
        return mv;

    }
    @GetMapping("/saveGame/{gameTitle}")
    public void searchGame(@PathVariable String gameTitle, @AuthenticationPrincipal UserDetails user, HttpServletResponse response) throws IOException {
        String username = user.getUsername();
        userService.saveGameToWishlist(gameTitle,username);
        response.sendRedirect("/wishlist");
    }
    @GetMapping("/myGames")
    public ModelAndView myGamesList(){
        ModelAndView mv = new ModelAndView("myGames");
        List<gameDetails> gameList = gameService.getGames();
        mv.addObject("games",gameList);
        return mv;
    }

}
