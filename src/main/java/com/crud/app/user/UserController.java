package com.crud.app.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAllUsers() { return ResponseEntity.ok(userService.findAllUsers()); }

    @GetMapping(path = "/first-name/ascending")
    public ResponseEntity<List<UserDTO>> findAllUsersByFirstName() { return ResponseEntity.ok(userService.findAllUsersByFirstName()); }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDTO> findUserByUserId(@PathVariable("userId") Integer userId) {

        UserDTO userDTO = userService.findUserByUserId(userId);

        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<UserDTO> deleteUserByUserId(@PathVariable("userId") Integer userId) {

        UserDTO userDTO = userService.deleteUserByUserId(userId);

        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}