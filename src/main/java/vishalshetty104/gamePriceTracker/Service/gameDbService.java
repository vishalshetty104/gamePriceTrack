package vishalshetty104.gamePriceTracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vishalshetty104.gamePriceTracker.Model.gameDetails;
import vishalshetty104.gamePriceTracker.Repository.gameRepository;

import java.util.List;

@Service
public class gameDbService {

    @Autowired
    gameRepository repository;

    public List<gameDetails> getGames(){
        return repository.findAll();
    }

    public void saveGameDetails(gameDetails game){
        repository.save(game);
    }
}
