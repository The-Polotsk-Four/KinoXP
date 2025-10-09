package dk.ek.backend.catalog.controller;

import dk.ek.backend.catalog.dto.UserDto;
import dk.ek.backend.catalog.model.User;
import dk.ek.backend.catalog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<List> login(@RequestParam(required = false)String email, String password) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session){

        User user = userService.getUserByEmailEntity(email);
        if (user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        session.setAttribute("loggedInUser", user);
        return ResponseEntity.ok("Login successful");
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){

        UserDto created = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            UserDto updated = userService.updateUser(id, userDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
