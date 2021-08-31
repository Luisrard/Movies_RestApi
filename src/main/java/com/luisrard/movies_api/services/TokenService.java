package com.luisrard.movies_api.services;

import com.luisrard.movies_api.entities.Token;
import com.luisrard.movies_api.repositories.jpa.TokenRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenService implements IConfirmationTokenService<Token> {
    private final TokenRepo tokenRepo;

    @Override
    public void saveConfirmationToken(Token token) {
        tokenRepo.save(token);
    }

    @Override
    public Optional<Token> getToken(String token) {
        return tokenRepo.findByToken(token);
    }

    @Override
    public int setConfirmedAt(String token) {
        return tokenRepo.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
