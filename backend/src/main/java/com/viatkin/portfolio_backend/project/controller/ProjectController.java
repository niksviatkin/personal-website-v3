package com.viatkin.portfolio_backend.project.controller;

import com.viatkin.portfolio_backend.project.domain.Project;
import com.viatkin.portfolio_backend.project.service.ProjectService;
import com.viatkin.portfolio_backend.project.dto.CreateProjectRequest;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/projects")
    public ResponseEntity<Project> createProject(@Valid @RequestBody CreateProjectRequest request) {

        Project createdProject = projectService.createProject(request);

        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    // Future endpoints:
    // e.g., @GetMapping("/projects/{id}")
    // e.g., @PostMapping("/projects")
}
