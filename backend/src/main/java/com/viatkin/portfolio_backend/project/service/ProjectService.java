package com.viatkin.portfolio_backend.project.service;

import com.viatkin.portfolio_backend.project.domain.Project;
import com.viatkin.portfolio_backend.project.repository.ProjectRepository;
import com.viatkin.portfolio_backend.project.dto.CreateProjectRequest;
import com.viatkin.portfolio_backend.project.dto.ProjectResponse;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectResponse> getAllProjects() {
        List<Project> projects = projectRepository.findAll();

        return projects.stream()
                .map(this::mapToProjectResponse)
                .collect(Collectors.toList());
    }

    public ProjectResponse  createProject(CreateProjectRequest request) {
        Project newProject = new Project();
        newProject.setTitle(request.getTitle());
        newProject.setDescription(request.getDescription());
        newProject.setImageUrl(request.getImageUrl());
        newProject.setRepoUrl(request.getRepoUrl());
        newProject.setLiveUrl(request.getLiveUrl());
        newProject.setTechnologies(request.getTechnologies());

        Project savedProject = projectRepository.save(newProject);

        return mapToProjectResponse(savedProject);
    }

    private ProjectResponse mapToProjectResponse(Project project) {
        ProjectResponse response = new ProjectResponse();

        response.setId(project.getId());
        response.setTitle(project.getTitle());
        response.setDescription(project.getDescription());
        response.setImageUrl(project.getImageUrl());
        response.setRepoUrl(project.getRepoUrl());
        response.setLiveUrl(project.getLiveUrl());
        response.setTechnologies(project.getTechnologies());

        return response;
    }
}
