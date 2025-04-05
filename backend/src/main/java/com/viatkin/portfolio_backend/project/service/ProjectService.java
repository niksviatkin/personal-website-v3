package com.viatkin.portfolio_backend.project.service;

import com.viatkin.portfolio_backend.project.domain.Project;
import com.viatkin.portfolio_backend.project.repository.ProjectRepository;
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
}
