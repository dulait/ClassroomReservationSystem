package rs.ac.bg.fon.njt.server.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.server.Enums.ResponseStatus;
import rs.ac.bg.fon.njt.server.Enums.UserType;
import rs.ac.bg.fon.njt.server.Models.Password;
import rs.ac.bg.fon.njt.server.Models.User;
import rs.ac.bg.fon.njt.server.Repositories.UserRepository;
import rs.ac.bg.fon.njt.server.Utils.*;

import rs.ac.bg.fon.njt.server.Models.Token;


@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordService passwordService;
    private final TempPasswordService tempPasswordService;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final UserRepository userRepository;

    @Override
    public Response<String> registerUser(String email) {
        if (email == null) {
            return new Response<>(ResponseStatus.BadRequest, "Invalid email address.");
        }

        Response<User> response = userService.findUserByEmail(email);
        if (response.getStatus() == ResponseStatus.Ok) {
            User existingUser = response.getData();
            if (existingUser.getUserType() == UserType.User) {
                return new Response<>(ResponseStatus.Conflict, "User with email " + email + " already exists.");
            }
        } else {
            return new Response<>(ResponseStatus.BadRequest, "User with email " + email + " doesn't exist in the system.");
        }

        String tempPassword = PasswordUtil.generateTempPassword();
        tempPasswordService.saveTempPassword(response.getData(), tempPassword);
        emailService.sendTempPassword(email, tempPassword);

        return new Response<>(ResponseStatus.Ok, "Success. Temporary password sent to email: " + email);
    }

    @Override
    public Response<String> verifyTempPassword(String email, String tempPassword) {
        if (email == null || tempPassword == null) {
            return new Response<>(ResponseStatus.BadRequest, "Invalid parameters.");
        }

        Response<User> userResponse = userService.findUserByEmail(email);
        if (userResponse.getStatus() != ResponseStatus.Ok) {
            return new Response<>(ResponseStatus.NotFound, "User with email " + email + " doesn't exist in the system.");
        }

        User user = userResponse.getData();
        if (!tempPasswordService.isTempPassword(user, tempPassword)) {
            return new Response<>(ResponseStatus.Unauthorized, "Invalid temporary password.");
        }

        return new Response<>(ResponseStatus.Ok, "Temporary password verified.");
    }

    @Transactional
    @Override
    public Response<String> setNewPassword(String email, String newPassword) {
        if (email == null || newPassword == null) {
            return new Response<>(ResponseStatus.BadRequest, "Invalid parameters.");
        }

        Response<User> userResponse = userService.findUserByEmail(email);
        if (userResponse.getStatus() != ResponseStatus.Ok) {
            return new Response<>(ResponseStatus.NotFound, "User with email " + email + " doesn't exist in the system.");
        }

        User user = userResponse.getData();

        Password password = new Password();
        password.setUser(user);
        password.setPasswordHash(newPassword);
        passwordService.savePassword(password);

        tempPasswordService.invalidateTempPassword(user);

        user.setUserType(UserType.User);
        userRepository.save(user);
        return new Response<>(ResponseStatus.Ok, "Password successfully set.");
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
