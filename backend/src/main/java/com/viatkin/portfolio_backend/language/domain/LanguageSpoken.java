package com.viatkin.portfolio_backend.language.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "languages_spoken")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanguageSpoken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

}
