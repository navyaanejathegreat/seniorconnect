package com.seniorconnect.seniorconnect.controller;

import com.seniorconnect.seniorconnect.model.User;
import com.seniorconnect.seniorconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> req) {
        if (userRepository.existsByEmail(req.get("email")))
            return ResponseEntity.badRequest().body("Email already in use");

        User user = new User();
        user.setName(req.get("name"));
        user.setEmail(req.get("email"));
        user.setPasswordHash(passwordEncoder.encode(req.get("password")));
        user.setRole(User.Role.valueOf(req.get("role").toUpperCase()));
        userRepository.save(user);

        return ResponseEntity.ok("Registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        User user = userRepository.findByEmail(req.get("email"))
                .orElse(null);

        if (user == null)
            return ResponseEntity.status(401).body("User not found");

        if (!passwordEncoder.matches(req.get("password"), user.getPasswordHash()))
            return ResponseEntity.status(401).body("Wrong password");

        return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "role", user.getRole(),
                "name", user.getName(),
                "id", user.getId()
        ));
    }
}