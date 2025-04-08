package com.viatkin.portfolio_backend.skill.repository;

import com.viatkin.portfolio_backend.skill.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    Set<Skill> findByIdIn(Set<Long> ids);

}