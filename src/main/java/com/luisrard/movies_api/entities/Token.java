package com.luisrard.movies_api.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Confirmation token to the clients
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiredAt;
    @Column(name = "ConfirmedAt")
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(
            nullable = false, name = "user"
    )
    private User user;

    public Token(String token, LocalDateTime createdAt, LocalDateTime expiredAt, LocalDateTime confirmedAt, User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.confirmedAt = confirmedAt;
        this.user = user;
    }
}
