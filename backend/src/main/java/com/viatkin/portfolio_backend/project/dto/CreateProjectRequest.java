package com.viatkin.portfolio_backend.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class CreateProjectRequest {

    @NotBlank(message = "Project title cannot be blank") // Validation rule
    @Size(max = 100, message = "Project title cannot exceed 100 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @URL(message = "Image URL must be a valid URL") // Basic URL validation
    private String imageUrl;

    @URL(message = "Repo URL must be a valid URL")
    private String repoUrl;

    @URL(message = "Live URL must be a valid URL")
    private String liveUrl;

    private String technologies;
}
