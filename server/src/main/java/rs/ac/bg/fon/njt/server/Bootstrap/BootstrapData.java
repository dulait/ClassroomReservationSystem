package rs.ac.bg.fon.njt.server.Bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.server.Enums.UserType;
import rs.ac.bg.fon.njt.server.Models.User;
import rs.ac.bg.fon.njt.server.Repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        User user = new User();
        user.setEmail("draskovicdusan4@gmail.com");
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            user.setUserType(UserType.NotRegistered);
            userRepository.saveAndFlush(user);
        }

        User registered = new User();
        registered.setEmail("dd19102021@gmail.com");
        if (userRepository.findByEmail(registered.getEmail()).isEmpty()) {
            registered.setUserType(UserType.User);
            userRepository.saveAndFlush(registered);
        }
    }
}
