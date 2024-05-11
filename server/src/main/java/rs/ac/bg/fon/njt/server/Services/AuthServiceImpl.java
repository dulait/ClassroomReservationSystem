package rs.ac.bg.fon.njt.server.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.server.Enums.ResponseStatus;
import rs.ac.bg.fon.njt.server.Enums.UserType;
import rs.ac.bg.fon.njt.server.Models.Password;
import rs.ac.bg.fon.njt.server.Models.TempPassword;
import rs.ac.bg.fon.njt.server.Models.User;
import rs.ac.bg.fon.njt.server.Utils.*;

import java.util.Date;


@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordService passwordService;
    private final TempPasswordService tempPasswordService;
    private final TokenService tokenService;
    private final EmailService emailService;

    @Override
    public Response<String> registerUser(String email) {
        if (email == null) {
            return new Response<>(ResponseStatus.BadRequest, "Invalid email address.");
        }

        Response<User> existingUserResponse = userService.findUserByEmail(email);
        if (existingUserResponse.getStatus() == ResponseStatus.Ok) {
            User existingUser = existingUserResponse.getData();
            if (existingUser.getUserType() != UserType.NotRegistered) {
                return new Response<>(ResponseStatus.Conflict, "User with email " + email + " already exists.");
            }
        } else {
            return new Response<>(ResponseStatus.BadRequest, "User with email " + email + " doesn't exist in the system.");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUserType(UserType.NotRegistered);
        Response<User> createUserResponse = userService.createNewUser(newUser);
        if (createUserResponse.getStatus() != ResponseStatus.Ok) {
            return new Response<>(ResponseStatus.InternalServerError, "Failed to register user.");
        }

        String tempPassword = PasswordUtil.generateTempPassword();
        tempPasswordService.saveTempPassword(createUserResponse.getData(), tempPassword);
        emailService.sendTempPassword(email, tempPassword);

        return new Response<>(ResponseStatus.Ok, "Success. Temporary password sent to email: " + email);
    }

    public Response<String> loginUser(String email, String password) {
        // todo implement
        return new Response<>(ResponseStatus.Ok);
    }

    public Response<String> changePassword(String email, String oldPassword, String newPassword) {
        // todo implement
        return new Response<>(ResponseStatus.Ok);
    }
}
