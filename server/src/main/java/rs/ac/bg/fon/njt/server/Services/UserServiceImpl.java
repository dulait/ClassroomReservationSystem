package rs.ac.bg.fon.njt.server.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.server.Enums.ResponseStatus;
import rs.ac.bg.fon.njt.server.Models.Password;
import rs.ac.bg.fon.njt.server.Repositories.PasswordRepository;
import rs.ac.bg.fon.njt.server.Models.User;
import rs.ac.bg.fon.njt.server.Repositories.UserRepository;
import rs.ac.bg.fon.njt.server.Utils.Response;

import java.util.List;
import java.util.Optional;
import rs.ac.bg.fon.njt.server.Models.Token;
import rs.ac.bg.fon.njt.server.Repositories.TokenRepository;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;
    private final TokenRepository tokenRepository;

    @Override
    public Response<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
         return new Response<>(ResponseStatus.Ok, users);
       
      
    }

    @Override
    public Response<User> findUserById(Long id) {
        if (id == null || id <= 0) {
            return new Response<>(ResponseStatus.BadRequest);
        }
        Optional<User> user = userRepository.findById(id);

        return user.map(
                        value -> new Response<>(ResponseStatus.Ok, value))
                .orElseGet(() -> new Response<>(ResponseStatus.NotFound)
                );
    }

    @Override
    public Response<User> findUserByEmail(String email) {
        if (email == null || email.isBlank()) {
            return new Response<>(ResponseStatus.BadRequest);
        }

        Optional<User> user = userRepository.findByEmail(email);

        return user.map(
                        value -> new Response<>(ResponseStatus.Ok, value))
                .orElseGet(() -> new Response<>(ResponseStatus.NotFound)
                );
    }

    @Override
    public Response<User> findUserByCredentials(String email, String password) {
        if (email == null || password == null) {
            return new Response<>(ResponseStatus.BadRequest);
        }

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return new Response<>(ResponseStatus.NotFound);
        }

        Optional<Password> userPassword = passwordRepository.findByUserId(user.get().getId());

        if (userPassword.isEmpty() || !userPassword.get().getPasswordHash().equals(password)) {
            return new Response<>(ResponseStatus.Unauthorized);
        }

        return new Response<>(ResponseStatus.Ok, user.get());
    }

    @Override
    public Response<User> createNewUser(User user) {
        if (user == null) {
            return new Response<>(ResponseStatus.BadRequest);
        }

        userRepository.save(user);
        return new Response<>(ResponseStatus.Ok, user);
    }

    @Override
    public Response<User> updateExistingUser(User user) {
        if (user == null || user.getId() == null) {
            return new Response<>(ResponseStatus.BadRequest);
        }

        if (!userRepository.existsById(user.getId())) {
            return new Response<>(ResponseStatus.NotFound);
        }

        userRepository.save(user);
        return new Response<>(ResponseStatus.Ok, user);
    }

    @Override
    public Response<User> deleteExistingUser(Long id) {
        if (id == null || id <= 0) {
            return new Response<>(ResponseStatus.BadRequest);
        }

        if (!userRepository.existsById(id)) {
            return new Response<>(ResponseStatus.NotFound);
        }

        userRepository.deleteById(id);
        return new Response<>(ResponseStatus.NoContent);
    }
    
    public boolean hasPassword(User user){
        Optional<Password> password = passwordRepository.findByUserId(user.getId());
        if(password.isPresent()){
            return true;
        }
        
        return false;
    }

    @Override
    public Response<User> findUserByToken(String token) {
        if(token == null){
            return new Response<>(ResponseStatus.BadRequest);
        }
        Optional<Token> tokenResponse = tokenRepository.findByToken(token);
        if(!tokenResponse.isPresent()){
            return new Response<>(ResponseStatus.InternalServerError);
        }
        
        User user = tokenResponse.get().getUser();
        
        return new Response<>(ResponseStatus.Ok, user);
    }
}
