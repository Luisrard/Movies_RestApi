package com.luisrard.movies_api.controllers;

import com.luisrard.movies_api.entities.User;
import com.luisrard.movies_api.services.AppUserService;
import com.luisrard.movies_api.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final AppUserService userService;
    @PostMapping
    public ResponseEntity<?> register(@RequestBody User request){
        registrationService.registerUser(request);
        return ResponseEntity.ok(true);
    }
    @GetMapping("/confirm")
    public String confirm(String token) {
        return registrationService.confirmToken(token);
    }
    @GetMapping
    public UserDetails getUser(String email){
       return userService.loadUserByUsername(email);
    }
}
