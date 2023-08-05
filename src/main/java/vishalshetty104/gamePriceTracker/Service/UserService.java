package vishalshetty104.gamePriceTracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vishalshetty104.gamePriceTracker.Controller.dto.userDTO;
import vishalshetty104.gamePriceTracker.Model.User;
import vishalshetty104.gamePriceTracker.Repository.userRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private userRepository repository;

    public void saveUser(userDTO userDTO){
        User newUser = new User(userDTO.getUsername(), userDTO.getPassword());
        repository.save(newUser);
    }
    public void saveGameToWishlist(String gameTitle, String username){
        User user = repository.findByUsername(username).get();
        List<String> wishlist = user.getWishlist();
        if(!wishlist.contains(gameTitle))wishlist.add(gameTitle);
        user.setWishlist(wishlist);
        repository.save(user);
    }
    public User getUserByUsername(String username){
        return repository.findByUsername(username).get();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        }
        else{
            throw new UsernameNotFoundException("User doesn't exist");
        }
    }
}
