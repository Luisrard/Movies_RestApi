package com.luisrard.movies_api.services;

import com.luisrard.movies_api.entities.Token;
import com.luisrard.movies_api.entities.User;
import com.luisrard.movies_api.entities.enums.AppUserRole;
import com.luisrard.movies_api.repositories.jpa.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService, IAppUserService<User> {
    private final UserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;
    private final TokenService confirmationTokenService;
    private static final String MSG_USER_NOT_FOUND = "User with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findByMail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(MSG_USER_NOT_FOUND, email)));
    }

    @Override
    public String signUpUser(User appUser) {
        boolean present = appUserRepo.findByMail(appUser.getMail()).isPresent();

        if(present){
            throw new IllegalStateException("Email already taken");
        }

        String passwordEncoded = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(passwordEncoded);

        appUser.setRole(AppUserRole.USER);
        appUserRepo.save(appUser);

        String token = UUID.randomUUID().toString();
        Token confirmationToken = new Token(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                null,
                appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    @Override
    public int enableAppUser(String email) {
        return appUserRepo.enableAppUser(email);
    }
}