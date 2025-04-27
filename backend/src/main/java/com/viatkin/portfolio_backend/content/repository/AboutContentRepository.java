package com.viatkin.portfolio_backend.content.repository;

import com.viatkin.portfolio_backend.content.domain.AboutContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutContentRepository extends JpaRepository<AboutContent, Long> {
    // Standard methods are enough for now (findById, save)
    // Optional: Define a method if you need to ensure finding ID=1 specifically
    // Optional<AboutContent> findById(Long id); // JpaRepository already provides this
}