package com.viatkin.portfolio_backend.project.service;

import com.viatkin.portfolio_backend.project.domain.Project;
import com.viatkin.portfolio_backend.project.repository.ProjectRepository;
import com.viatkin.portfolio_backend.project.dto.CreateProjectRequest;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(CreateProjectRequest request) {

        Project newProject = new Project();

        newProject.setTitle(request.getTitle());
        newProject.setDescription(request.getDescription());
        newProject.setImageUrl(request.getImageUrl());
        newProject.setRepoUrl(request.getRepoUrl());
        newProject.setLiveUrl(request.getLiveUrl());
        newProject.setTechnologies(request.getTechnologies());

        return projectRepository.save(newProject);
    }

}
