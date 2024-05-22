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

import org.apache.commons.lang3.RandomStringUtils;
import rs.ac.bg.fon.njt.server.Enums.TokenType;
import rs.ac.bg.fon.njt.server.Models.Token;


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
        if (email == null | password == null) {
            return new Response<>(ResponseStatus.BadRequest, "Invalid email address or password.");
        }
        Response<User> existingUserResponse = userService.findUserByCredentials(email, password);

        if (existingUserResponse.getStatus() == ResponseStatus.NotFound) {
            return new Response<>(ResponseStatus.NotFound, "User with email " + email + " doesn't exist in the system.");
        }
        if (existingUserResponse.getStatus() == ResponseStatus.Unauthorized) {
            return new Response<>(ResponseStatus.Unauthorized, "Wrong password");
        }
        User existingUser = existingUserResponse.getData();
        String token = tokenService.generateJwtToken(existingUser);
        return new Response<>(ResponseStatus.Ok, token);
    }

    @Override
    public Response<String> requestPasswordChange(String email) {
        if (email == null) {
            return new Response<>(ResponseStatus.BadRequest, "Invalid email.");
        }
        Response<User> currentUserResponse = userService.findUserByEmail(email);
        if (!(currentUserResponse.getStatus() == ResponseStatus.Ok)) {
            return new Response<>(ResponseStatus.NotFound, "User with provided email does not exist.");
        }

        if (!userService.hasPassword(currentUserResponse.getData())) {
            return new Response<>(ResponseStatus.InternalServerError, "The user does not have a password.");
        }

        Token token = tokenService.createPasswordChangeToken(currentUserResponse.getData());
        String tokenString = token.getToken();

        emailService.sendPasswordChangeLink(email, tokenString);

        return new Response<>(ResponseStatus.Ok, "Password change link sent successfully.");
    }

    @Override
    public Response<String> changePassword(String token, String newPassword) {
        if (token == null || newPassword == null) {
            return new Response<>(ResponseStatus.BadRequest, "Invalid parameters.");
        }
        Response<User> userResponse = userService.findUserByToken(token);
        if (!(userResponse.getStatus() == ResponseStatus.Ok)) {
            return new Response<>(ResponseStatus.InternalServerError, "User with this token does not exist.");
        }
        Password password = new Password();
        password.setUser(userResponse.getData());
        password.setPasswordHash(newPassword);

        passwordService.updatePassword(password);
        return new Response<>(ResponseStatus.Ok, "Password successfully changed.");
    }
}
