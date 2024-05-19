package rs.ac.bg.fon.njt.server.Services;

import rs.ac.bg.fon.njt.server.Models.User;
import rs.ac.bg.fon.njt.server.Utils.Response;

import java.util.List;

public interface UserService {

    Response<List<User>> getAllUsers();

    Response<User> findUserById(Long id);

    Response<User> findUserByEmail(String email);

    Response<User> findUserByCredentials(String email, String password);

    Response<User> createNewUser(User user);

    Response<User> updateExistingUser(User user);

    Response<User> deleteExistingUser(Long id);
    
    Response<User> findUserByToken(String token);
    
    boolean hasPassword(User user);
    
}
