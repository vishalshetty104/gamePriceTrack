package vishalshetty104.gamePriceTracker.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vishalshetty104.gamePriceTracker.Controller.dto.userDTO;
import vishalshetty104.gamePriceTracker.Model.User;
import vishalshetty104.gamePriceTracker.Model.gameDetails;
import vishalshetty104.gamePriceTracker.Service.UserService;
import vishalshetty104.gamePriceTracker.Service.gameAPIService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    gameAPIService gameAPIService;
    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/login")
    public ModelAndView loginPage(){
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("user", new userDTO());
        return mv;
    }
    @GetMapping("/register")
    public ModelAndView userRegisterPage(){
        ModelAndView mv = new ModelAndView("register");
        mv.addObject("user", new userDTO());
        return mv;
    }
    @PostMapping("/register")
    public void registerUser(@ModelAttribute("user") userDTO userData, HttpServletResponse response) throws IOException {
        userData.setPassword(encoder.encode(userData.getPassword()));
        userService.saveUser(userData);
        response.sendRedirect("/login");
    }

    @GetMapping("/wishlist")
    public ModelAndView userWishlist(@AuthenticationPrincipal UserDetails currentUser) throws JsonProcessingException {
        ModelAndView mv = new ModelAndView("wishlist");
        User user = userService.getUserByUsername(currentUser.getUsername());
        mv.addObject("user",user);
        List<String> wishlist = user.getWishlist();
        List<gameDetails> game = new ArrayList<>();
        for(int i=0;i<wishlist.size();i++){
            game.add(gameAPIService.getGameDetails(wishlist.get(i)));
        }
        mv.addObject("games",game);
        return mv;
    }

}
