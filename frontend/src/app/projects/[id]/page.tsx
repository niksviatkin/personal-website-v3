import React from 'react';
import type {Project} from '@/types/project';
import {notFound} from 'next/navigation';
import StyledLink from '@/components/ui/StyledLink';

// TODO: Define the API base URL (replace with env variable later)

const API_BASE_URL = 'http://localhost:8080';

async function getProject(id: string): Promise<Project | null> {
    try {
        const res = await fetch(`${API_BASE_URL}/v1/projects/${id}`, { cache: 'no-store' });

        if (!res.ok) {
            if (res.status === 404) {
                notFound();
            }
            console.error(`Failed to fetch project ${id}: ${res.status} ${res.statusText}`);
            return null;
        }

        return await res.json();
    } catch (error) {
        console.error(`Network or parsing error fetching project ${id}:`, error);
        return null;
    }
}

type Props = {
    params: Promise<{ id: string }>;
    // searchParams?: { [key: string]: string | string[] | undefined }; // Optional: if we need to search params
};

export default async function ProjectDetailPage(props: Props) {
    const params = await props.params;
    const project = await getProject(params.id);

    if (!project) {
        return <div className="container mx-auto p-4 text-center text-red-500">Failed to load project data.</div>;
    }

    return (
        <main className="container mx-auto p-4 md:p-8">
            <div className="mb-6">
                {/* Use StyledLink for back navigation */}
                <StyledLink href="/projects" variant="default">
                    &larr; Back to Projects
                </StyledLink>
            </div>
            <article className="bg-white dark:bg-gray-800 rounded-lg shadow-md p-6">
                <h1 className="text-3xl font-bold mb-4">{project.title}</h1>

                {project.imageUrl && (
                    // TODO: Using simple img tag for now. Consider Next/Image later for optimization.
                    // eslint-disable-next-line @next/next/no-img-element
                    (<img src={project.imageUrl} alt={project.title} className="w-full md:w-2/3 lg:w-1/2 h-auto rounded-md mb-4 mx-auto" />)
                )}

                <div className="prose dark:prose-invert max-w-none mb-4"> {/* Use prose for nice text formatting */}
                    <p>{project.description || "No description available."}</p>
                </div>

                {project.technologies && (
                    <p className="text-sm text-gray-600 dark:text-gray-400 mb-4">
                        <strong>Technologies:</strong> {project.technologies}
                    </p>
                )}

                <div className="flex flex-wrap gap-4 mt-4"> {/* Added mt-4 */}
                    {project.repoUrl && (
                        <StyledLink href={project.repoUrl} target="_blank" rel="noopener noreferrer" variant="primaryAction">
                            View Code (GitHub)
                        </StyledLink>
                    )}
                    {project.liveUrl && (
                        <StyledLink href={project.liveUrl} target="_blank" rel="noopener noreferrer" variant="secondaryAction">
                            View Live Demo
                        </StyledLink>
                    )}
                </div>
            </article>
        </main>
    );
}