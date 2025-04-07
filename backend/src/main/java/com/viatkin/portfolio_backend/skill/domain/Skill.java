package com.viatkin.portfolio_backend.skill.domain;

import java.util.Set;
import java.util.HashSet; // Import HashSet for initialization
import com.viatkin.portfolio_backend.project.domain.Project;
import com.viatkin.portfolio_backend.resume.domain.ResumeContent;
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

    @ManyToMany(mappedBy = "technologies")
    private Set<Project> projects = new HashSet<>();

    @ManyToMany(mappedBy = "skills")
    private Set<ResumeContent> resumes = new HashSet<>();
}