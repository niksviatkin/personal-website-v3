import React from 'react';
import type { Project } from '@/types/project';
import StyledLink from './StyledLink';

interface ProjectCardProps {
  project: Project;
}

const ProjectCard: React.FC<ProjectCardProps> = ({ project }) => {
  return (
    <div className="flex h-full flex-col overflow-hidden rounded-lg border bg-white shadow-md dark:border-gray-700 dark:bg-gray-800">
      {project.imageUrl && project.imageUrl !== '/images/placeholder.png' && (
        // eslint-disable-next-line @next/next/no-img-element
        <img
          src={project.imageUrl}
          alt={project.title}
          className="h-48 w-full object-cover"
        />
      )}
      <div className="flex flex-grow flex-col p-4">
        <h2 className="mb-2 text-xl font-semibold">{project.title}</h2>
        <p className="mb-3 flex-grow text-sm text-gray-600 dark:text-gray-400">
          {project.description || 'No description available.'}
        </p>
        {project.technologies && (
          <p className="mt-auto mb-4 text-xs text-gray-500 dark:text-gray-300">
            <span className="font-medium">Technologies:</span>{' '}
            {project.technologies}
          </p>
        )}
        {/* Links */}
        <div className="mt-2 flex justify-start space-x-4">
          {project.repoUrl && (
            <StyledLink
              href={project.repoUrl}
              target="_blank"
              rel="noopener noreferrer"
              variant="primaryAction"
            >
              View Code (GitHub)
            </StyledLink>
          )}
          {project.liveUrl && (
            <StyledLink
              href={project.liveUrl}
              target="_blank"
              rel="noopener noreferrer"
              variant="secondaryAction"
            >
              Live Demo
            </StyledLink>
          )}
        </div>
      </div>
    </div>
  );
};

export default ProjectCard;
