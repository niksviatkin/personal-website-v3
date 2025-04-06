package com.viatkin.portfolio_backend.config;

import com.viatkin.portfolio_backend.project.domain.Project;
import com.viatkin.portfolio_backend.project.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    private final ProjectRepository projectRepository;

    public DataInitializer(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Checking for existing project data (dev profile active)...");

        if (projectRepository.count() == 0) {
            log.info("No project data found. Seeding sample projects...");

            Project project1 = new Project(
                    null, // ID is auto-generated
                    "Personal Portfolio V3",
                    "This very website! Built with Next.js, Spring Boot, PostgreSQL, deployed on Vercel and OCI.",
                    "/images/portfolio-v3.png", // Example image path (replace later)
                    "https://github.com/niksviatkin/personal-website-v3",
                    "https://viatkin.com", // Example live URL (replace later)
                    "Next.js, React, TypeScript, Tailwind CSS, Java, Spring Boot, PostgreSQL, Docker, OCI, Vercel"
            );

            Project project2 = new Project(
                    null,
                    "E-commerce Platform Mockup",
                    "A conceptual backend for an e-commerce site featuring product catalogs and order management.",
                    "/images/ecommerce.png",
                    "https://github.com/niksviatkin/ecommerce-mockup",
                    null, // No live URL for this one
                    "Java, Spring Boot, Spring Data JPA, PostgreSQL, REST API"
            );

            Project project3 = new Project(
                    null,
                    "Task Management App API",
                    "A simple REST API for managing tasks and to-do lists.",
                    "/images/task-app.png",
                    "https://github.com/niksviatkin/task-api",
                    null,
                    "Java, Spring Boot, REST API, H2 (In-Memory)" // Example using different tech
            );

            // Save all sample projects to the database
            projectRepository.saveAll(List.of(project1, project2, project3));

            log.info("Sample projects seeded successfully.");

        } else {
            log.info("Project data already exists. Skipping seeding.");
        }
    }
}
