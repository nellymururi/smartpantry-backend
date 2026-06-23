package com.smartpantry.backend.user;


import com.google.firebase.auth.FirebaseToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Called after every login from frontend
    @PostMapping("/sync")
    public ResponseEntity<User> syncUser(Authentication authentication) {
        FirebaseToken token = (FirebaseToken) authentication.getPrincipal();
        User user = userService.syncUser(token);
        return ResponseEntity.ok(user);
    }

    // Get current user profile
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        FirebaseToken token = (FirebaseToken) authentication.getPrincipal();
        User user = userService.getCurrentUser(token.getUid());
        return ResponseEntity.ok(user);
    }
}