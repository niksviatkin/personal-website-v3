package com.viatkin.portfolio_backend.skill.domain;

import com.viatkin.portfolio_backend.skill.domain.SkillCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
// Import Set, Project, ResumeContent later if adding ManyToMany mappedBy

@Entity
@Table(name = "skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "icon_identifier")
    private String iconIdentifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private SkillCategory category;

    // --- Inverse side of ManyToMany relationships (Add later) ---
    // @ManyToMany(mappedBy = "technologies") // In Project entity
    // private Set<Project> projects;

    // @ManyToMany(mappedBy = "skills") // In ResumeContent entity
    // private Set<ResumeContent> resumes;
}