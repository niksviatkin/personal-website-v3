import React from 'react';

export default function AboutPage() {
    return (
        <main className="max-w-3xl mx-auto p-4">
            <h1 className="text-3xl font-bold mb-6">About Me</h1>

            {/* TODO: Optional: Add a different photo here? */}

            <div className="prose dark:prose-invert max-w-none space-y-4"> {/* Using Tailwind prose for nice text formatting */}
                <p>
                    Hello! I&#39;m Nikita Viatkin, a passionate software developer based in Reynoldsburg, Ohio. My journey into technology started with [Your Story - e.g., a specific project, curiosity, education]. I thrive on solving complex problems and building efficient, scalable applications.
                </p>
                <p>
                    I have experience across the full stack, with a strong focus on backend development using Java and the Spring Boot framework. I enjoy designing RESTful APIs, working with databases like PostgreSQL, and ensuring code quality. On the frontend, I&#39;m proficient with modern JavaScript frameworks like React and Next.js, utilizing tools like TypeScript and Tailwind CSS to create engaging user interfaces.
                </p>
                <p>
                    Beyond coding, I&#39;m interested in [Your Interests - e.g., DevOps practices with Docker, cloud platforms like OCI/AWS, specific tech areas like AI/ML, a hobby]. I&#39;m always eager to learn new technologies and contribute to challenging projects.
                </p>
                <p>
                    Currently seeking opportunities where I can leverage my skills to build impactful software solutions. Feel free to browse my projects or get in touch!
                </p>
            </div>
        </main>
    );
}