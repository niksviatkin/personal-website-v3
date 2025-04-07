package com.viatkin.portfolio_backend.certification.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "certifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String issuer;

    @Column(name = "date_issued")
    private String dateIssued;

    @Column(name = "verify_url")
    private String verifyUrl;

    @Column(name = "display_order")
    private Integer displayOrder;
}