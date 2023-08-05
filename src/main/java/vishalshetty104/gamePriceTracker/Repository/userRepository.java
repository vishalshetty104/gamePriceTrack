package vishalshetty104.gamePriceTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vishalshetty104.gamePriceTracker.Model.User;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
