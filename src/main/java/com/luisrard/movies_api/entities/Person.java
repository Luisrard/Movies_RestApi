package com.luisrard.movies_api.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 80, unique = true)
    private String name;
    private LocalDate birthDate;
    @Column(length = 60)
    private String nickname;
    @Column(length = 400)
    private String bio;
}
