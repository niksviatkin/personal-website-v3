package com.viatkin.portfolio_backend.project.dto;

import com.viatkin.portfolio_backend.skill.dto.SkillResponse;
import lombok.Data;
import java.util.Set;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String repoUrl;
    private String liveUrl;
    private Integer displayOrder;
    private Set<SkillResponse> technologies;
}
