package com.spring.tutorial.redditclone.services;

import com.spring.tutorial.redditclone.DTO.RegisterRequest;
import com.spring.tutorial.redditclone.model.User;
import com.spring.tutorial.redditclone.model.VerificationToken;
import com.spring.tutorial.redditclone.repository.UserRepository;
import com.spring.tutorial.redditclone.repository.VerificationTokenRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    /**
     * @FieldInjection
     *
     * @ConstructorInjection
     *
     * @FiledInjection-vs-@ConstructorInjection
     *
     */

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    @Transactional
    public void signup(RegisterRequest registerRequest) {

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
    }

    private String generateVerificationToken(User user) {
        String verificaitonToken = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(verificaitonToken);
        verificationToken.setUser(user);

        return verificaitonToken;
    }
}
