package com.viatkin.portfolio_backend.project.service;

import com.viatkin.portfolio_backend.project.domain.Project;
import com.viatkin.portfolio_backend.project.repository.ProjectRepository;
import com.viatkin.portfolio_backend.project.dto.CreateProjectRequest;
import com.viatkin.portfolio_backend.project.dto.ProjectResponse;
import com.viatkin.portfolio_backend.error.ResourceNotFoundException;
import com.viatkin.portfolio_backend.project.dto.UpdateProjectRequest;
import com.viatkin.portfolio_backend.skill.domain.Skill;
import com.viatkin.portfolio_backend.skill.repository.SkillRepository;
import com.viatkin.portfolio_backend.skill.dto.SkillResponse;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Set;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final SkillRepository skillRepository;

    public ProjectService(ProjectRepository projectRepository, SkillRepository skillRepository) {
        this.projectRepository = projectRepository;
        this.skillRepository = skillRepository;
    }

    public List<ProjectResponse> getAllProjects() {
        List<Project> allProjects = projectRepository.findAll();
        return allProjects.stream()
                .map(this::createProjectResponse)
                .collect(Collectors.toList());
    }

    public ProjectResponse createProject(CreateProjectRequest request) {
        Project newProject = new Project();
        newProject.setTitle(request.getTitle());
        newProject.setDescription(request.getDescription());
        newProject.setImageUrl(request.getImageUrl());
        newProject.setRepoUrl(request.getRepoUrl());
        newProject.setLiveUrl(request.getLiveUrl());
        // newProject.setDisplayOrder(...); // Set display order if applicable

        if (request.getTechnologyIds() != null && !request.getTechnologyIds().isEmpty()) {
            Set<Skill> technologies = skillRepository.findByIdIn(request.getTechnologyIds());
            // TODO: Optional: Check if all requested IDs were found
            newProject.setTechnologies(technologies);
        } else {
            newProject.setTechnologies(new HashSet<>());
        }

        Project savedProject = projectRepository.save(newProject);
        return createProjectResponse(savedProject); // Use existing helper
    }

    public ProjectResponse getProjectById(Long id) {
        Project project = getProjectByIdOrThrow(id);
        return createProjectResponse(project);
    }

    public ProjectResponse updateProject(Long id, UpdateProjectRequest request) {
        Project existingProject = getProjectByIdOrThrow(id);

        // Update simple fields
        existingProject.setTitle(request.getTitle());
        existingProject.setDescription(request.getDescription());
        existingProject.setImageUrl(request.getImageUrl());
        existingProject.setRepoUrl(request.getRepoUrl());
        existingProject.setLiveUrl(request.getLiveUrl());
        // existingProject.setDisplayOrder(...); // Update display order if applicable

        Set<Skill> technologies = new HashSet<>();
        if (request.getTechnologyIds() != null && !request.getTechnologyIds().isEmpty()) {
            technologies = skillRepository.findByIdIn(request.getTechnologyIds());
            // TODO: Optional: Check if all requested IDs were found
        }
        existingProject.setTechnologies(technologies);

        Project updatedProject = projectRepository.save(existingProject);
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

    private ProjectResponse createProjectResponse(Project project) {
        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setTitle(project.getTitle());
        response.setDescription(project.getDescription());
        response.setImageUrl(project.getImageUrl());
        response.setRepoUrl(project.getRepoUrl());
        response.setLiveUrl(project.getLiveUrl());
        response.setDisplayOrder(project.getDisplayOrder());

        if (project.getTechnologies() != null) {
            response.setTechnologies(
                    project.getTechnologies().stream()
                            .map(this::mapToSkillResponse)
                            .collect(Collectors.toSet())
            );
        } else {
            response.setTechnologies(Collections.emptySet());
        }

        return response;
    }

    private SkillResponse mapToSkillResponse(Skill skill) {
        return new SkillResponse(
                skill.getId(),
                skill.getName(),
                skill.getIconIdentifier()
        );
    }
}
