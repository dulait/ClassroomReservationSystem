package rs.ac.bg.fon.njt.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.server.Models.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
