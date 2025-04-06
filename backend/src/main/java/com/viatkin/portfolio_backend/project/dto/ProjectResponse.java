package com.viatkin.portfolio_backend.project.dto;

import lombok.Data;

@Data
public class ProjectResponse {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String repoUrl;
    private String liveUrl;
    private String technologies;
}
