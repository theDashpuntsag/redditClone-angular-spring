package com.spring.tutorial.redditclone.services;

import com.spring.tutorial.redditclone.DTO.RegisterRequest;
import com.spring.tutorial.redditclone.exeptions.SpringRedditException;
import com.spring.tutorial.redditclone.model.NotificationEmail;
import com.spring.tutorial.redditclone.model.User;
import com.spring.tutorial.redditclone.model.VerificationToken;
import com.spring.tutorial.redditclone.repository.UserRepository;
import com.spring.tutorial.redditclone.repository.VerificationTokenRepository;
import com.spring.tutorial.redditclone.services.custom.MailService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
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
    private final MailService mailService;

    public void signup(RegisterRequest registerRequest) {

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        VerificationToken verificationToken = new VerificationToken();
        mailService.sendEmail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());
    }


    // ========== PRIVATE FUNCTIONS =========
    private String generateVerificationToken(User user) {
        String verificaitonToken = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(verificaitonToken);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return verificaitonToken;
    }

    private void fetchUserAndEnable(VerificationToken verificationToken){
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
