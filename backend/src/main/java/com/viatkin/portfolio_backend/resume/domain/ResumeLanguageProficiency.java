package com.viatkin.portfolio_backend.resume.domain;


import com.viatkin.portfolio_backend.language.domain.LanguageSpoken;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "resume_language_proficiencies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeLanguageProficiency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many proficiencies belong to one resume content
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_content_id", nullable = false)
    private ResumeContent resumeContent;

    // Many proficiencies relate to one language
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private LanguageSpoken language;

    @Column(name = "proficiency_level", nullable = false)
    private String proficiencyLevel; // e.g., "Native", "Fluent", "B2", "Conversational"

    @Column(name = "display_order")
    private Integer displayOrder;
}