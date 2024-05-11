package rs.ac.bg.fon.njt.server.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.njt.server.Models.User;
import rs.ac.bg.fon.njt.server.Services.UserService;
import rs.ac.bg.fon.njt.server.Utils.Response;
import rs.ac.bg.fon.njt.server.Utils.ResponseConverter;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ResponseConverter<User> converter;

    /**
     * Get a list of all Users.
     *
     * @return list of all users.
     */
    @GetMapping
    public ResponseEntity<List<User>> index() {
        Response<List<User>> response = userService.getAllUsers();
        return converter.toListResponseEntity(response);
    }


    /**
     * Get a User by id.
     *
     * @param id the id of the User
     * @return the User with a status code of {@code 200 Ok} if the User with the given id exists, otherwise {@code 404 Not Found}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> show(@PathVariable(name = "id") Long id) {
        Response<User> response = userService.findUserById(id);
        return converter.toResponseEntity(response);
    }

    /**
     * Create a new User.
     *
     * @param user The User to be created.
     * @return {@code 200 Ok} if the User is created, otherwise {@code 400 Bad Request}.
     */
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        Response<User> response = userService.createNewUser(user);
        return converter.toResponseEntity(response);
    }

    /**
     * Update an existing User.
     *
     * @param user The User to be updated
     * @return {@code 204 No Content} if the User is updated, otherwise {@code 400 Bad Request}.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user) {
        Response<User> response = userService.updateExistingUser(user);
        return converter.toResponseEntity(response);
    }


    /**
     * Delete an existing User.
     *
     * @param id The id of the User to be deleted.
     * @return {@code 204 No Content} if the User is deleted, otherwise {@code 400 Bad Request}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<User> destroy(@PathVariable("id") Long id) {
        Response<User> response = userService.deleteExistingUser(id);
        return converter.toResponseEntity(response);
    }

}
