import React from 'react';
import type {Project} from '@/types/project';

// TODO: IMPORTANT: replace this with an environment variable later!
const API_URL = 'http://localhost:8080/v1/projects';

// Async function to fetch data
async function getProjects(): Promise<Project[]> {
    try {
        // { cache: 'no-store' } ensures fresh data on each request during development.
        // TODO: Remove or adjust caching strategy for production later.
        const res = await fetch(API_URL, { cache: 'no-store' });

        if (!res.ok) {
            console.error(`Failed to fetch projects: ${res.status} ${res.statusText}`);
            return [];
        }

        return await res.json();
    } catch (error) {
        console.error("Error fetching projects (Network/Parsing Error):", error);
        return [];
    }
}

export default async function ProjectsPage() {
    const projects = await getProjects();

    return (
        <main className="p-4"> {/* Add some padding */}
            <h1 className="text-2xl font-bold mb-4">My Projects</h1>
            {projects.length === 0 ? (
                <p>No projects found.</p>
            ) : (
                <ul>
                    {projects.map((project) => (
                        <li key={project.id} className="mb-2 border-b pb-1">
                            {/* Display only the title for now */}
                            {project.title}
                        </li>
                    ))}
                </ul>
            )}
        </main>
    );
}