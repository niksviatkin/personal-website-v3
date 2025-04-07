package com.viatkin.portfolio_backend.skill.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "skill_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkillCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "display_order")
    private Integer displayOrder;

}
