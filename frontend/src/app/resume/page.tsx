import React from 'react';
import {
  BlocksRenderer,
  type BlocksContent,
} from '@strapi/blocks-react-renderer'; // Import Strapi Blocks Renderer and type
import type { StrapiResumeResponse, ResumeData } from '@/types/strapi'; // Import updated types
import Button from '@/components/ui/Button';
import StyledLink from '@/components/ui/StyledLink';
import { notFound } from 'next/navigation'; // Can potentially use if fetch fails critically

const STRAPI_URL = process.env.STRAPI_API_URL;

async function getResumeContent(): Promise<ResumeData | null> {
  // Basic check if URL is configured
  if (!STRAPI_URL) {
    console.error('Strapi API URL environment variable is not configured.');
    return null;
  }
  try {
    // Populate everything, including the 'certifications' component field and relations
    const fullUrl = `${STRAPI_URL}/resume?populate=*`; // Using populate=deep might be easier than listing all relations/components
    // Alternative: Explicit populate: ?populate[experience_items]=*&populate[education_items]=*&populate[certifications]=*
    console.log('Fetching Resume from:', fullUrl); // Optional debugging

    const res = await fetch(fullUrl, { cache: 'no-store' }); // Fetch fresh data in dev

    if (!res.ok) {
      console.error(`Failed to fetch Resume: ${res.status} ${res.statusText}`);
      // You could potentially trigger notFound() here for critical fetch failures
      if (res.status === 404) notFound();
      return null;
    }
    const jsonResponse: StrapiResumeResponse = await res.json();

    // Extract the data object containing all attributes
    const resumeData = jsonResponse.data ?? null;
    console.log(
      'Fetched Resume data:',
      resumeData ? '[Resume Data Object]' : resumeData
    ); // Optional debugging
    return resumeData;
  } catch (error) {
    console.error('Error fetching Resume:', error);
    return null;
  }
}

// This page doesn't receive dynamic params like [id], so props are simpler
export default async function ResumePage() {
  const resume = await getResumeContent(); // resume is ResumeData | null

  // Handle case where data couldn't be fetched
  if (!resume) {
    return (
      <main className="mx-auto max-w-4xl p-4 text-center text-red-500 md:p-8">
        Error loading resume data. Please ensure the CMS is running and content
        is published.
      </main>
    );
  }

  // Now we access everything from the 'resume' object fetched from Strapi

  return (
    <main className="mx-auto max-w-4xl rounded-lg bg-white p-4 shadow-lg md:p-8 dark:bg-gray-800">
      {/* --- Header section using Strapi data --- */}
      <div className="mb-8 text-center">
        {resume.contact_name && (
          <h1 className="text-4xl font-bold">{resume.contact_name}</h1>
        )}
        {resume.contact_pageTitle && (
          <p className="text-xl text-gray-600 dark:text-gray-400">
            {resume.contact_pageTitle}
          </p>
        )}
        {resume.contact_location && (
          <p className="text-sm text-gray-500 dark:text-gray-300">
            {resume.contact_location}
          </p>
        )}
        <div className="mt-2 flex flex-wrap justify-center gap-x-4 gap-y-1 text-blue-600 dark:text-blue-400">
          {resume.contact_email && (
            <a href={`mailto:${resume.contact_email}`}>
              {resume.contact_email}
            </a>
          )}
          {resume.contact_linkedinUrl && (
            <StyledLink
              href={resume.contact_linkedinUrl}
              target="_blank"
              rel="noopener noreferrer"
              variant="default"
            >
              LinkedIn
            </StyledLink>
          )}
          {resume.contact_githubUrl && (
            <StyledLink
              href={resume.contact_githubUrl}
              target="_blank"
              rel="noopener noreferrer"
              variant="default"
            >
              GitHub
            </StyledLink>
          )}
        </div>
        <div className="mt-4">
          <Button
            href="/Nikita_Viatkin_Resume.pdf" // Ensure PDF is in /frontend/public/
            download
            variant="primary"
            size="sm"
          >
            Download PDF Resume
          </Button>
        </div>
      </div>

      {/* --- Dynamic Sections from Strapi --- */}
      {resume.summary && (
        <section className="mb-6">
          <h2 className="mb-3 border-b pb-1 text-2xl font-semibold">Summary</h2>
          <p className="text-gray-700 dark:text-gray-300">{resume.summary}</p>
        </section>
      )}

      <section className="mb-6">
        <h2 className="mb-3 border-b pb-1 text-2xl font-semibold">Skills</h2>
        <div className="space-y-1 text-gray-700 dark:text-gray-300">
          {resume.skills_languages && (
            <p>
              <strong>Languages:</strong> {resume.skills_languages}
            </p>
          )}
          {resume.skills_frameworks && (
            <p>
              <strong>Frameworks:</strong> {resume.skills_frameworks}
            </p>
          )}
          {resume.skills_databases && (
            <p>
              <strong>Databases:</strong> {resume.skills_databases}
            </p>
          )}
          {resume.skills_tools && (
            <p>
              <strong>Tools & Techniques:</strong> {resume.skills_tools}
            </p>
          )}
          {resume.skills_other && (
            <p>
              <strong>Other:</strong> {resume.skills_other}
            </p>
          )}
        </div>
      </section>

      {resume.experience_items && resume.experience_items.length > 0 && (
        <section className="mb-6">
          <h2 className="mb-3 border-b pb-1 text-2xl font-semibold">
            Experience
          </h2>
          {resume.experience_items.map((item) => (
            <div key={item.id} className="mb-4 last:mb-0">
              <h3 className="text-lg font-bold">{item.title}</h3>
              <p className="font-semibold">
                {item.company} {item.location && `| ${item.location}`}
              </p>
              <p className="mb-1 text-sm text-gray-500 dark:text-gray-300">
                {item.years}
              </p>
              {/* Use Strapi Blocks Renderer for description */}
              {item.description && (
                <div className="prose prose-sm dark:prose-invert mt-1 max-w-none">
                  <BlocksRenderer content={item.description as BlocksContent} />{' '}
                  {/* Cast to BlocksContent */}
                </div>
              )}
            </div>
          ))}
        </section>
      )}

      {resume.education_items && resume.education_items.length > 0 && (
        <section className="mb-6">
          <h2 className="mb-3 border-b pb-1 text-2xl font-semibold">
            Education
          </h2>
          {resume.education_items.map((item) => (
            <div key={item.id} className="mb-4 last:mb-0">
              <h3 className="text-lg font-bold">{item.degree}</h3>
              <p className="font-semibold">{item.university}</p>
              <p className="mb-1 text-sm text-gray-500 dark:text-gray-300">
                {item.years}
              </p>
              {item.details && (
                <p className="text-sm text-gray-700 dark:text-gray-300">
                  {item.details}
                </p>
              )}
            </div>
          ))}
        </section>
      )}

      {/* Certifications Section from Strapi Component */}
      {resume.certifications && resume.certifications.length > 0 && (
        <section className="mb-6">
          <h2 className="mb-3 border-b pb-1 text-2xl font-semibold">
            Certifications
          </h2>
          <ul className="list-inside list-disc space-y-1 text-gray-700 dark:text-gray-300">
            {resume.certifications.map((cert) => (
              <li key={cert.id}>
                {' '}
                {/* Use component instance id */}
                {cert.name}{' '}
                {cert.issuer &&
                  `(${cert.issuer}${cert.date ? `, ${cert.date}` : ''})`}
                {cert.link && (
                  <StyledLink
                    href={cert.link}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="ml-2 text-xs"
                    variant="default"
                  >
                    [Verify]
                  </StyledLink>
                )}
              </li>
            ))}
          </ul>
        </section>
      )}

      {/* Languages & Interests Sections from Strapi */}
      {resume.languages && (
        <section className="mb-6">
          <h2 className="mb-3 border-b pb-1 text-2xl font-semibold">
            Languages
          </h2>
          <p className="text-gray-700 dark:text-gray-300">{resume.languages}</p>
        </section>
      )}
      {resume.interests && (
        <section>
          <h2 className="mb-3 border-b pb-1 text-2xl font-semibold">
            Interests
          </h2>
          <p className="text-gray-700 dark:text-gray-300">{resume.interests}</p>
        </section>
      )}
    </main>
  );
}
