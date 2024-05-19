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

    @PostMapping("/request-password-change")
    public ResponseEntity<String> requestPasswordChange(@RequestParam("email") String email) {
        Response<String> response = authService.requestPasswordChange(email);
        return converter.toResponseEntity(response);
    }
    
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam("token") String token,
                                                @RequestParam("new-password") String newPassword) {
        Response<String> response = authService.changePassword(token, newPassword);
        return converter.toResponseEntity(response);
    }
}
