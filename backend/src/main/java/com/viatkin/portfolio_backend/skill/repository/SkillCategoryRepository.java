package com.viatkin.portfolio_backend.skill.repository;

import com.viatkin.portfolio_backend.skill.domain.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {

}
