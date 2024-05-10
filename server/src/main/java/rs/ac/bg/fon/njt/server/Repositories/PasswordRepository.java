package rs.ac.bg.fon.njt.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.server.Models.Password;

import java.util.Optional;

public interface PasswordRepository extends JpaRepository<Password, Long> {
    Optional<Password> findByUserId(Long userId);
}