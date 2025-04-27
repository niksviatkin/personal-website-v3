package com.viatkin.portfolio_backend.config;

import com.viatkin.portfolio_backend.content.repository.AboutContentRepository;
import com.viatkin.portfolio_backend.project.domain.Project;
import com.viatkin.portfolio_backend.project.repository.ProjectRepository;
import com.viatkin.portfolio_backend.skill.domain.Skill;
import com.viatkin.portfolio_backend.skill.domain.SkillCategory;
import com.viatkin.portfolio_backend.skill.repository.SkillCategoryRepository;
import com.viatkin.portfolio_backend.skill.repository.SkillRepository;
import com.viatkin.portfolio_backend.user.domain.User;
import com.viatkin.portfolio_backend.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.viatkin.portfolio_backend.content.service.AboutContentService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    private final ProjectRepository projectRepository;
    private final SkillCategoryRepository skillCategoryRepository;
    private final SkillRepository skillRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AboutContentService aboutContentService;

    public DataInitializer(ProjectRepository projectRepository,
                           SkillCategoryRepository skillCategoryRepository,
                           SkillRepository skillRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AboutContentService aboutContentService) {
        this.aboutContentService = aboutContentService;
        this.projectRepository = projectRepository;
        this.skillCategoryRepository = skillCategoryRepository;
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Checking initial data (dev profile active)...");

        if (userRepository.count() == 0) {
            log.warn("!!! No users found. Seeding default admin user !!!");
            String adminUsername = "admin";
            String adminPassword = "password"; // CHANGE THIS in a real scenario
            User adminUser = new User(
                    null,
                    adminUsername,
                    passwordEncoder.encode(adminPassword),
                    "ROLE_ADMIN",
                    true
            );
            userRepository.save(adminUser);
            log.warn("*********************************************************************");
            log.warn("** Created default admin user: username='{}', password='{}' **", adminUsername, adminPassword);
            log.warn("** CHANGE THIS PASSWORD IMMEDIATELY if used outside local dev! **");
            log.warn("*********************************************************************");
        } else {
            log.info("Users already exist. Skipping admin user seeding.");
        }

        SkillCategory catBackend = null;
        SkillCategory catFrontend = null;
        SkillCategory catDb = null;
        SkillCategory catTool = null;
        if (skillCategoryRepository.count() == 0) {
            log.info("Seeding Skill Categories...");
            catBackend = skillCategoryRepository.save(new SkillCategory(null, "Backend", 1));
            catFrontend = skillCategoryRepository.save(new SkillCategory(null, "Frontend", 2));
            catDb = skillCategoryRepository.save(new SkillCategory(null, "Database", 3));
            catTool = skillCategoryRepository.save(new SkillCategory(null, "Tool", 4));
            log.info("Skill Categories seeded.");
        } else {
            log.info("Skill Categories already exist.");
        }

        Skill skillJava;
        Skill skillSpring;
        Skill skillReact;
        Skill skillNext;
        Skill skillTs;
        Skill skillDocker;
        Skill skillPostgres;
        if (skillRepository.count() == 0 && catBackend != null) {
            log.info("Seeding Skills...");
            skillJava = skillRepository.save(new Skill(null, "Java", "java", catBackend, null, null));
            skillSpring = skillRepository.save(new Skill(null, "Spring Boot", "spring", catBackend, null, null));
            skillReact = skillRepository.save(new Skill(null, "React", "react", catFrontend, null, null));
            skillNext = skillRepository.save(new Skill(null, "Next.js", "nextjs", catFrontend, null, null));
            skillTs = skillRepository.save(new Skill(null, "TypeScript", "typescript", catFrontend, null, null));
            skillPostgres = skillRepository.save(new Skill(null, "PostgreSQL", "postgresql", catDb, null, null));
            skillDocker = skillRepository.save(new Skill(null, "Docker", "docker", catTool, null, null));
            log.info("Skills seeded.");
        } else {
            log.info("Skills already exist or categories not seeded.");
            skillJava = skillRepository.findById(1L).orElse(null);
            skillSpring = skillRepository.findById(2L).orElse(null);
            skillReact = skillRepository.findById(3L).orElse(null);
            skillNext = skillRepository.findById(4L).orElse(null);
            skillTs = skillRepository.findById(5L).orElse(null);
            skillPostgres = skillRepository.findById(6L).orElse(null);
            skillDocker = skillRepository.findById(7L).orElse(null);
        }

        if (projectRepository.count() == 0) {
            log.info("Seeding sample projects...");

            Set<Skill> techP1 = new HashSet<>();
            if (skillNext != null) techP1.add(skillNext);
            if (skillSpring != null) techP1.add(skillSpring);
            if (skillPostgres != null) techP1.add(skillPostgres);
            if (skillDocker != null) techP1.add(skillDocker);
            // Add others as needed...

            Set<Skill> techP2 = new HashSet<>();
            if (skillJava != null) techP2.add(skillJava);
            if (skillSpring != null) techP2.add(skillSpring);
            if (skillPostgres != null) techP2.add(skillPostgres);


            Project project1 = new Project(
                    null,
                    "Personal Portfolio V3",
                    "This very website! Built with Next.js, Spring Boot, PostgreSQL, deployed on Vercel and OCI.",
                    "/images/portfolio-v3.png",
                    "https://github.com/niksviatkin/personal-website-v3",
                    "https://viatkin.com",
                    1,
                    techP1
            );
            Project project2 = new Project(null, "E-commerce Platform Mockup", "...", "/images/ecommerce.png", "...", null, 2, techP2);
            Project project3 = new Project(null, "Task Management App API", "...", "/images/task-app.png", "...", null, 3, new HashSet<>(List.of(skillJava, skillSpring)));


            projectRepository.saveAll(List.of(project1, project2, project3));
            log.info("Sample projects seeded.");
        } else {
            log.info("Project data already exists. Skipping seeding.");
        }

        try {
            aboutContentService.initializeAboutContent(
                    "About Me (Default)", // Default title
                    "Please update this content via the admin panel." // Default body
            );
            log.info("Checked/Initialized AboutContent.");
        } catch (Exception e) {
            log.error("Failed to initialize AboutContent", e);
        }
    }
}
