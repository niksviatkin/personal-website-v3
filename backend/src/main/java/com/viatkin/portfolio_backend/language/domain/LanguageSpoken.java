package com.viatkin.portfolio_backend.language.domain;

import java.util.List;
import java.util.ArrayList;
import com.viatkin.portfolio_backend.resume.domain.ResumeLanguageProficiency;

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

    @OneToMany(
            mappedBy = "language",
            cascade = CascadeType.ALL, // Example cascade
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ResumeLanguageProficiency> resumeProficiencies = new ArrayList<>();
}
