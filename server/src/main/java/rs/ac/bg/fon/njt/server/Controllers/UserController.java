package rs.ac.bg.fon.njt.server.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.njt.server.Models.User;
import rs.ac.bg.fon.njt.server.Services.UserService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Get a list of all Users.
     *
     * @return list of all users.
     */
    @GetMapping
    public ResponseEntity<List<User>> index() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Get a User by id.
     *
     * @param id the id of the User
     * @return the User with a status code of {@code 200 Found} if the User with the given id exists, otherwise {@code 404 Not Found}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> show(@PathVariable(name = "id") Long id) {
        Optional<User> found = userService.findUserById(id);
        return found.map(user -> new ResponseEntity<>(user, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Create a new User.
     *
     * @param user The User to be created.
     * @return {@code 200 Ok} if the User is created, otherwise {@code 400 Bad Request}.
     */
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        boolean success = userService.createNewUser(user);
        return success ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Update an existing User.
     *
     * @param user The User to be updated
     * @return {@code 204 No Content} if the User is updated, otherwise {@code 400 Bad Request}.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user) {
        boolean success = userService.updateExistingUser(user);
        return success ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Delete an existing User.
     *
     * @param id The id of the User to be deleted.
     * @return {@code 204 No Content} if the User is deleted, otherwise {@code 400 Bad Request}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<User> destroy(@PathVariable("id") Long id) {
        boolean success = userService.deleteExistingUser(id);
        return success ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
