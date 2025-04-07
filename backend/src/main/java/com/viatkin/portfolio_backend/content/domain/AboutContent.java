package com.viatkin.portfolio_backend.content.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "about_content")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AboutContent {

    @Id
    private Long id; // e.g., Always 1

    @Column
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String body;
}