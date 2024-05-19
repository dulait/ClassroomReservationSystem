package rs.ac.bg.fon.njt.server.Repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.server.Models.Token;
import rs.ac.bg.fon.njt.server.Models.User;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
    List<Token> findByExpiresAtBefore(Date now);
}
