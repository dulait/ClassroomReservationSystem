package rs.ac.bg.fon.njt.server.Bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.server.Builders.ClassroomBuilder;
import rs.ac.bg.fon.njt.server.Enums.ClassroomType;
import rs.ac.bg.fon.njt.server.Enums.UserType;
import rs.ac.bg.fon.njt.server.Models.Classroom;
import rs.ac.bg.fon.njt.server.Models.User;
import rs.ac.bg.fon.njt.server.Repositories.ClassroomRepository;
import rs.ac.bg.fon.njt.server.Repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ClassroomRepository classroomRepository;

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

        Classroom classroom = new ClassroomBuilder()
                .ofType(ClassroomType.ComputerRoom)
                .withName("RC-10")
                .withNumberOfSeats(40)
                .withNumberOfComputers(40)
                .withNote("Racunarska sala za lab vezbe")
                .isActive(false)
                .build();

        Classroom classroom2 = new ClassroomBuilder()
                .ofType(ClassroomType.Amphitheatre)
                .withName("B015")
                .withNumberOfSeats(120)
                .withNumberOfComputers(40)
                .withNote("Amfiteatar u staroj zgradi")
                .isActive(true)
                .build();

        classroomRepository.saveAndFlush(classroom);
        classroomRepository.saveAndFlush(classroom2);
    }
}
