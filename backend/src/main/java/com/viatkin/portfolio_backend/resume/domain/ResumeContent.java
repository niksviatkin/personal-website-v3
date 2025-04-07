package com.viatkin.portfolio_backend.resume.domain;

import com.viatkin.portfolio_backend.skill.domain.Skill;
import com.viatkin.portfolio_backend.interest.domain.Interest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "resume_content")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeContent {

    @Id
    private Long id = 1L;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_page_title")
    private String contactPageTitle;

    @Column(name = "contact_location")
    private String contactLocation;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_linkedin_url")
    private String contactLinkedinUrl;

    @Column(name = "contact_github_url")
    private String contactGithubUrl;


    // Relationship to Skills (Many-to-Many via join table)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "resume_skill",
            joinColumns = @JoinColumn(name = "resume_content_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    // Relationship to Interests (Many-to-Many via join table)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "resume_interest",
            joinColumns = @JoinColumn(name = "resume_content_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    private Set<Interest> interests = new HashSet<>();

    // Relationship to Languages (One-to-Many via join entity ResumeLanguageProficiency)
    // 'resumeContent' is the field in ResumeLanguageProficiency mapping back to this entity
    @OneToMany(
            mappedBy = "resumeContent",
            cascade = CascadeType.ALL, // If deleting ResumeContent deletes proficiencies
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ResumeLanguageProficiency> languageProficiencies = new ArrayList<>();

}
