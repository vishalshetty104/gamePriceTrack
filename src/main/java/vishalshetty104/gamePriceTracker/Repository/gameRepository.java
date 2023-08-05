package vishalshetty104.gamePriceTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vishalshetty104.gamePriceTracker.Model.gameDetails;

public interface gameRepository extends JpaRepository<gameDetails, Long> {
}
