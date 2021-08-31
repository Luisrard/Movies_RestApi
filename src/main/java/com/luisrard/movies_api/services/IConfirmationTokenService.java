package com.luisrard.movies_api.services;

import java.util.Optional;

public interface IConfirmationTokenService<T> {
    void saveConfirmationToken(T token);

    Optional<T> getToken(String token);

    int setConfirmedAt(String token);
}
