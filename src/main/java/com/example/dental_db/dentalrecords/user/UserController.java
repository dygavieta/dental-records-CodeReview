package com.example.dental_db.dentalrecords.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UserController {

    @Autowired UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
        return ResponseEntity.created(uri).body(userRepository.save(user));
    }

}
