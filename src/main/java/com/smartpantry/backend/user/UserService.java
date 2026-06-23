package com.smartpantry.backend.user;


import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User syncUser(FirebaseToken token) {
        return userRepository.findByFirebaseUid(token.getUid())
                .map(existingUser -> {
                    // Update fields in case they changed in Firebase
                    existingUser.setEmail(token.getEmail());
                    existingUser.setDisplayName(token.getName());
                    existingUser.setPhotoUrl(token.getPicture());
                    return userRepository.save(existingUser);
                })
                .orElseGet(() -> {
                    // First login — create user in our DB
                    User newUser = User.builder()
                            .firebaseUid(token.getUid())
                            .email(token.getEmail())
                            .displayName(token.getName())
                            .photoUrl(token.getPicture())
                            .role("USER")
                            .build();
                    return userRepository.save(newUser);
                });
    }

    public User getCurrentUser(String firebaseUid) {
        return userRepository.findByFirebaseUid(firebaseUid)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
