package rs.ac.bg.fon.njt.server.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.server.Models.Password;
import rs.ac.bg.fon.njt.server.Repositories.PasswordRepository;
import rs.ac.bg.fon.njt.server.Models.User;
import rs.ac.bg.fon.njt.server.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        if (id == null || id <= 0) {
            return Optional.empty();
        }
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        if (email == null || email.isBlank()) {
            return Optional.empty();
        }
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserByCredentials(String email, String password) {
        if (email == null || password == null) {
            return Optional.empty();
        }

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return Optional.empty();
        }

        Optional<Password> userPassword = passwordRepository.findByUserId(user.get().getId());

        if (userPassword.isEmpty() || !userPassword.get().getPasswordHash().equals(password)) {
            return Optional.empty();
        }

        return user;
    }

    @Override
    public boolean createNewUser(User user) {
        if (user == null) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean updateExistingUser(User user) {
        if (user == null || user.getId() == null || !userRepository.existsById(user.getId())) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteExistingUser(Long id) {
        if (id == null || id <= 0 || !userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

}
