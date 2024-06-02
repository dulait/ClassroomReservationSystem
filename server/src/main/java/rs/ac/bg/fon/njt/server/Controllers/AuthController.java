package rs.ac.bg.fon.njt.server.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.njt.server.DTOs.LoginUserDto;
import rs.ac.bg.fon.njt.server.DTOs.RegisterUserDto;
import rs.ac.bg.fon.njt.server.DTOs.SetNewPasswordDto;
import rs.ac.bg.fon.njt.server.DTOs.VerifyTempPasswordDto;
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
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserDto dto) {
        Response<String> response = authService.registerUser(dto.email());
        return converter.toResponseEntity(response);
    }

    @PostMapping("/verify-temp-password")
    public ResponseEntity<String> verifyTempPassword(@RequestBody VerifyTempPasswordDto dto) {
        Response<String> response = authService.verifyTempPassword(dto.email(), dto.tempPassword());
        return converter.toResponseEntity(response);
    }

    @PostMapping("/set-new-password")
    public ResponseEntity<String> setNewPassword(@RequestBody SetNewPasswordDto dto) {
        Response<String> response = authService.setNewPassword(dto.email(), dto.newPassword());
        return converter.toResponseEntity(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginUserDto dto) {
        Response<String> response = authService.loginUser(dto.email(), dto.password());
        return converter.toResponseEntity(response);
    }

    @PostMapping("/request-password-change")
    public ResponseEntity<String> requestPasswordChange(@RequestBody String email) {
        Response<String> response = authService.requestPasswordChange(email);
        return converter.toResponseEntity(response);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody String token,
                                                 @RequestBody String newPassword) {
        Response<String> response = authService.changePassword(token, newPassword);
        return converter.toResponseEntity(response);
    }
}
