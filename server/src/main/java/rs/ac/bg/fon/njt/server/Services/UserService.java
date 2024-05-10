package rs.ac.bg.fon.njt.server.Services;

import rs.ac.bg.fon.njt.server.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByCredentials(String email, String password);

    boolean createNewUser(User user);

    boolean updateExistingUser(User user);

    boolean deleteExistingUser(Long id);

}
