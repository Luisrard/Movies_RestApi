package com.luisrard.movies_api.repositories.jpa;

import com.luisrard.movies_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByMail(String mail);

    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.enabled = TRUE WHERE u.mail = ?1")
    int enableAppUser(String email);
}
