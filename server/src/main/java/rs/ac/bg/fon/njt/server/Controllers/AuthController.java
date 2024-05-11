package rs.ac.bg.fon.njt.server.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.server.Services.*;
import rs.ac.bg.fon.njt.server.Utils.Response;
import rs.ac.bg.fon.njt.server.Utils.ResponseConverter;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final ResponseConverter<String> converter;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam("email") String email) {
        Response<String> response = authService.registerUser(email);
        return converter.toResponseEntity(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        Response<String> response = authService.loginUser(email, password);
        return converter.toResponseEntity(response);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam("email") String email,
                                                 @RequestParam("oldPassword") String oldPassword,
                                                 @RequestParam("newPassword") String newPassword) {
        Response<String> response = authService.changePassword(email, oldPassword, newPassword);
        return converter.toResponseEntity(response);
    }

}
