package com.viatkin.portfolio_backend.interest.domain;

import java.util.Set;
import java.util.HashSet;
import com.viatkin.portfolio_backend.resume.domain.ResumeContent;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "interests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "interests")
    private Set<ResumeContent> resumes = new HashSet<>();
}