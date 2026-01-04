package com.Music.App.controller;

import com.Music.App.model.User;
import com.Music.App.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return ResponseEntity.ok(userService.registerUser(user));
    }
    @GetMapping("/{username}")
    public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
    @GetMapping("/{email}")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @GetMapping
    public ResponseEntity<List<User>> getPassiveUser (User user){
        return ResponseEntity.ok(userService.getPassiveUser());
    }

}
