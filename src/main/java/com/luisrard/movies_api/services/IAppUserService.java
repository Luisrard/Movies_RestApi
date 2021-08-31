package com.luisrard.movies_api.services;

public interface IAppUserService<U> {
    String signUpUser( U appUser);
    int enableAppUser(String email);
}
