import React from 'react';
import type {Project} from '@/types/project';
import Link from 'next/link';

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
                        <div key={project.id} className="border rounded-lg shadow-md overflow-hidden bg-white dark:bg-gray-800 dark:border-gray-700">
                            {project.imageUrl && project.imageUrl !== "/images/placeholder.png" && ( // Example: Conditionally show image
                                // TODO: Using simple img tag for now. Consider Next/Image later for optimization.
                                // eslint-disable-next-line @next/next/no-img-element
                                <img src={project.imageUrl} alt={project.title} className="w-full h-48 object-cover" />
                            )}

                            <div className="p-4">
                                <h2 className="text-xl font-semibold mb-2">{project.title}</h2>
                                <p className="text-gray-600 dark:text-gray-400 text-sm mb-3">
                                    {project.description || "No description available."}
                                </p>
                                {project.technologies && (
                                    <p className="text-xs text-gray-500 dark:text-gray-300 mb-4">
                                        <span className="font-medium">Technologies:</span> {project.technologies}
                                    </p>
                                )}

                                {/* Links */}
                                <div className="flex justify-start space-x-4">
                                    {project.repoUrl && (
                                        <Link href={project.repoUrl} target="_blank" rel="noopener noreferrer" className="text-blue-600 hover:text-blue-800 dark:text-blue-400 dark:hover:text-blue-300 text-sm">
                                            View Code (GitHub)
                                        </Link>
                                    )}
                                    {project.liveUrl && (
                                        <Link href={project.liveUrl} target="_blank" rel="noopener noreferrer" className="text-green-600 hover:text-green-800 dark:text-green-400 dark:hover:text-green-300 text-sm">
                                            Live Demo
                                        </Link>
                                    )}
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </main>
    );
}