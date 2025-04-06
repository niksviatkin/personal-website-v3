package com.viatkin.portfolio_backend.project.service;

import com.viatkin.portfolio_backend.project.domain.Project;
import com.viatkin.portfolio_backend.project.repository.ProjectRepository;
import com.viatkin.portfolio_backend.project.dto.CreateProjectRequest;
import com.viatkin.portfolio_backend.project.dto.ProjectResponse;
import com.viatkin.portfolio_backend.error.ResourceNotFoundException;
import com.viatkin.portfolio_backend.project.dto.UpdateProjectRequest;

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
        List<Project> allProjects = projectRepository.findAll();
        return allProjects.stream()
                .map(this::createProjectResponse)
                .collect(Collectors.toList());
    }

    public ProjectResponse createProject(CreateProjectRequest createRequest) {
        Project project = new Project();
        populateProjectFields(project, createRequest);
        Project savedProject = projectRepository.save(project);
        return createProjectResponse(savedProject);
    }

    public ProjectResponse getProjectById(Long id) {
        Project project = getProjectByIdOrThrow(id);
        return createProjectResponse(project);
    }

    public ProjectResponse updateProject(Long id, UpdateProjectRequest updateRequest) {
        Project projectToUpdate = getProjectByIdOrThrow(id);
        populateProjectFields(projectToUpdate, updateRequest);
        Project updatedProject = projectRepository.save(projectToUpdate);
        return createProjectResponse(updatedProject);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found with id: " + id);
        }
        projectRepository.deleteById(id);
    }

    private Project getProjectByIdOrThrow(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    private void populateProjectFields(Project project, CreateProjectRequest request) {
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setImageUrl(request.getImageUrl());
        project.setRepoUrl(request.getRepoUrl());
        project.setLiveUrl(request.getLiveUrl());
        project.setTechnologies(request.getTechnologies());
    }

    private void populateProjectFields(Project project, UpdateProjectRequest request) {
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setImageUrl(request.getImageUrl());
        project.setRepoUrl(request.getRepoUrl());
        project.setLiveUrl(request.getLiveUrl());
        project.setTechnologies(request.getTechnologies());
    }

    private ProjectResponse createProjectResponse(Project project) {
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
