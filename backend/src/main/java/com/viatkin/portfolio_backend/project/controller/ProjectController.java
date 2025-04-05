package com.viatkin.portfolio_backend.project.controller;

import com.viatkin.portfolio_backend.project.domain.Project;
import com.viatkin.portfolio_backend.project.service.ProjectService;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // Future endpoints:
    // e.g., @GetMapping("/projects/{id}")
    // e.g., @PostMapping("/projects")
}
