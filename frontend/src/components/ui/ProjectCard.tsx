import React from 'react';
import type { Project } from '@/types/project';
import StyledLink from './StyledLink';

interface ProjectCardProps {
    project: Project;
}

const ProjectCard: React.FC<ProjectCardProps> = ({ project }) => {
    return (
        <div className="border rounded-lg shadow-md overflow-hidden bg-white dark:bg-gray-800 dark:border-gray-700 h-full flex flex-col">
            {project.imageUrl && project.imageUrl !== "/images/placeholder.png" && (
                // eslint-disable-next-line @next/next/no-img-element
                <img src={project.imageUrl} alt={project.title} className="w-full h-48 object-cover" />
            )}
            <div className="p-4 flex flex-col flex-grow">
                <h2 className="text-xl font-semibold mb-2">{project.title}</h2>
                <p className="text-gray-600 dark:text-gray-400 text-sm mb-3 flex-grow">
                    {project.description || "No description available."}
                </p>
                {project.technologies && (
                    <p className="text-xs text-gray-500 dark:text-gray-300 mb-4 mt-auto">
                        <span className="font-medium">Technologies:</span> {project.technologies}
                    </p>
                )}
                {/* Links */}
                <div className="flex justify-start space-x-4 mt-2">
                    {project.repoUrl && (
                        <StyledLink href={project.repoUrl} target="_blank" rel="noopener noreferrer" variant="primaryAction">
                            View Code (GitHub)
                        </StyledLink>
                    )}
                    {project.liveUrl && (
                        <StyledLink href={project.liveUrl} target="_blank" rel="noopener noreferrer" variant="secondaryAction">
                            Live Demo
                        </StyledLink>
                    )}
                </div>
            </div>
        </div>
    );
};

export default ProjectCard;
