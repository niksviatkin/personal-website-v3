package com.viatkin.portfolio_backend.content.repository;

import com.viatkin.portfolio_backend.content.domain.AboutContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AboutContentRepository extends JpaRepository<AboutContent, Long> {

    @Override
    Optional<AboutContent> findById(Long id);
}