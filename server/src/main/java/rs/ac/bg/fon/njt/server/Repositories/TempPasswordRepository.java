package rs.ac.bg.fon.njt.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.server.Models.TempPassword;
import rs.ac.bg.fon.njt.server.Models.User;

import java.util.Optional;

public interface TempPasswordRepository extends JpaRepository<TempPassword, Long> {
    Optional<TempPassword> findByUser(User user);

    void deleteByUser(User user);
}
