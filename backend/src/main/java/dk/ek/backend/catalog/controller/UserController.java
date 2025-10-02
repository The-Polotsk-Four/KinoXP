package dk.ek.backend.catalog.controller;

import dk.ek.backend.catalog.dto.UserDto;
import dk.ek.backend.catalog.model.User;
import dk.ek.backend.catalog.repository.UserRepository;
import dk.ek.backend.catalog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{/id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
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
