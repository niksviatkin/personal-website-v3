package com.viatkin.portfolio_backend.education.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "education")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String degree;

    @Column(nullable = false)
    private String university;

    @Column
    private String years;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String details;

    @Column(name = "display_order")
    private Integer displayOrder;
}
