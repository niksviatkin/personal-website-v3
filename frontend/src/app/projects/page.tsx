import React from 'react';
import type { Project } from '@/types/project';
import Link from 'next/link';
import ProjectCard from '@/components/ui/ProjectCard';

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
        <main className="container mx-auto p-4 md:p-8">
            <h1 className="text-3xl font-bold mb-6 text-center">My Projects</h1>
            {!projects || projects.length === 0 ? (
                <p className="text-center text-gray-500">No projects found. Check back later!</p>
            ) : (
                <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                    {projects.map((project) => (
                        <Link key={project.id} href={`/projects/${project.id}`} className="block hover:shadow-lg transition-shadow duration-200 rounded-lg">
                            <ProjectCard project={project} />
                        </Link>
                    ))}
                </div>
            )}
        </main>
    );
}