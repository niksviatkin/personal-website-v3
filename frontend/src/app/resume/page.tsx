import React from 'react';
import Button from "@/components/ui/Button";
import StyledLink from "@/components/ui/StyledLink";

const resumeData = {
    name: "Nikita Viatkin",
    title: "Software Developer",
    location: "Reynoldsburg, Ohio",
    email: "niksviatkin@gmail.com",
    linkedin: "https://linkedin.com/in/niksviatkin",
    github: "https://github.com/niksviatkin",
    summary: "Driven and detail-oriented Software Developer with experience in full-stack development using Java/Spring Boot and React/Next.js. Seeking opportunities to contribute to innovative projects and continue learning in a challenging environment.",
    skills: {
        languages: "Java, JavaScript, TypeScript, SQL, HTML, CSS",
        frameworks: "Spring Boot, Spring Data JPA, Spring Security, React, Next.js",
        databases: "PostgreSQL, H2",
        tools: "Docker, Git, GitHub Actions, Maven, npm, Postman, IntelliJ, WebStorm, OCI Basics",
        other: "REST API Design, TDD (basic), Agile Methodologies (basic)",
    },
    experience: [
        {
            title: "Software Developer Bootcamp (Example)",
            company: "Example Tech Corp",
            location: "Columbus, OH",
            years: "Summer 2024",
            description: [
                "Contributed to the development of a web application using Java and Spring Boot.",
                "Implemented REST API endpoints for data retrieval.",
                "Collaborated with team members using Git and Agile practices.",
            ],
        },
        // TODO: Add more experience objects here
    ],
    education: [
        {
            degree: "Bachelor of Science in Computer Science (Example)",
            university: "Example University",
            years: "2020 - 2024",
            details: "Relevant coursework: Data Structures, Algorithms, Database Management, Web Development.",
        },
        // TODO: Add more education here
    ],
};
// --- End of manual data entry ---

export default function ResumePage() {
    return (
        <main className="max-w-4xl mx-auto p-4 md:p-8 bg-white dark:bg-gray-800 shadow-lg rounded-lg">
            <div className="text-center mb-8">
                <h1 className="text-4xl font-bold">{resumeData.name}</h1>
                <p className="text-xl text-gray-600 dark:text-gray-400">{resumeData.title}</p>
                <p className="text-sm text-gray-500 dark:text-gray-300">{resumeData.location}</p>
                <div className="mt-2 flex justify-center space-x-4 text-blue-600 dark:text-blue-400">
                    <a href={`mailto:${resumeData.email}`}>{resumeData.email}</a>
                    <StyledLink href={resumeData.linkedin} variant="default" target="_blank" rel="noopener noreferrer">LinkedIn</StyledLink>
                    <StyledLink href={resumeData.github} variant="default" target="_blank" rel="noopener noreferrer">GitHub</StyledLink>
                </div>
                {/* TODO: Optional: PDF Download Button */}
                <div className="mt-4">
                    <Button href="/Nikita_Viatkin_Resume.pdf" download variant="primary" size="sm">
                        Download PDF Resume
                    </Button>
                </div>
            </div>

            <section className="mb-6">
                <h2 className="text-2xl font-semibold border-b pb-1 mb-3">Summary</h2>
                <p className="text-gray-700 dark:text-gray-300">{resumeData.summary}</p>
            </section>

            <section className="mb-6">
                <h2 className="text-2xl font-semibold border-b pb-1 mb-3">Skills</h2>
                <div className="text-gray-700 dark:text-gray-300">
                    {Object.entries(resumeData.skills).map(([category, skills]) => (
                        <p key={category}><strong className="capitalize">{category}:</strong> {skills}</p>
                    ))}
                </div>
            </section>

            <section className="mb-6">
                <h2 className="text-2xl font-semibold border-b pb-1 mb-3">Experience</h2>
                {resumeData.experience.map((job, index) => (
                    <div key={index} className="mb-4">
                        <h3 className="text-lg font-bold">{job.title}</h3>
                        <p className="font-semibold">{job.company} | {job.location}</p>
                        <p className="text-sm text-gray-500 dark:text-gray-300 mb-1">{job.years}</p>
                        <ul className="list-disc list-inside text-gray-700 dark:text-gray-300 space-y-1">
                            {job.description.map((point, i) => <li key={i}>{point}</li>)}
                        </ul>
                    </div>
                ))}
            </section>

            <section>
                <h2 className="text-2xl font-semibold border-b pb-1 mb-3">Education</h2>
                {resumeData.education.map((edu, index) => (
                    <div key={index} className="mb-4">
                        <h3 className="text-lg font-bold">{edu.degree}</h3>
                        <p className="font-semibold">{edu.university}</p>
                        <p className="text-sm text-gray-500 dark:text-gray-300 mb-1">{edu.years}</p>
                        {edu.details && <p className="text-gray-700 dark:text-gray-300">{edu.details}</p>}
                    </div>
                ))}
            </section>
        </main>
    );
}