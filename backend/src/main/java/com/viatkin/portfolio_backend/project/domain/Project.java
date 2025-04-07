package com.viatkin.portfolio_backend.project.domain;
import com.viatkin.portfolio_backend.skill.domain.Skill;

import jakarta.persistence.*;

import java.util.Set;
import java.util.HashSet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "repo_url")
    private String repoUrl;

    @Column(name = "live_url")
    private String liveUrl;

    @Column(name = "display_order")
    private Integer displayOrder;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "project_technology",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> technologies = new HashSet<>(); // Represents skills used as technologies
}
