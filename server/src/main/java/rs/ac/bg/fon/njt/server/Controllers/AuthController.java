package rs.ac.bg.fon.njt.server.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.server.Models.User;
import rs.ac.bg.fon.njt.server.Services.EmailService;
import rs.ac.bg.fon.njt.server.Services.PasswordService;
import rs.ac.bg.fon.njt.server.Services.TokenService;
import rs.ac.bg.fon.njt.server.Services.UserService;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordService passwordService;
    private final EmailService emailService;

    /**
     * Registers a new User by sending a temporary password to the Email address of the User.
     *
     * @param email The email of the User to which the temporary password will be sent.
     * @return {@code 200 Ok} if the temporary password gets sent to the User's email, otherwise {@code 404 Not Found}.
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam("email") String email) {
        Optional<User> user = userService.findUserByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with email " + email + " does not exist.");
        }

        String tempPassword = emailService.sendTempPasswordToEmail(email);
        passwordService.generateTempPassword(tempPassword, user.get());
        return ResponseEntity.ok("Temporary password sent to email: " + email);
        // todo test
    }

    /**
     * Performs user login by validating the email and password of the User.
     *
     * @param email    The Email address of the User.
     * @param password The Password of the User.
     * @return {@code 401 Unauthorized} if the User has invalid credentials, otherwise {@code 200 Ok} and a JWT Token string.
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        Optional<User> user = userService.findUserByCredentials(email, password);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password");
        }

        String token = tokenService.generateJwtToken(user.get());
        return ResponseEntity.ok(token);
        // todo test
    }

    // todo implement
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam("email") String email,
                                                 @RequestParam("oldPassword") String oldPassword,
                                                 @RequestParam("newPassword") String newPassword) {
        return null;
    }

}
