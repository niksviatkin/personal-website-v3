import React from 'react';
import type { StrapiAboutPageResponse, AboutPageData } from '@/types/strapi'; // Import types

// Use environment variable (server-side access)
const STRAPI_URL = process.env.STRAPI_API_URL;

async function getAboutContent(): Promise<AboutPageData | null> {
    // Basic check if URL is configured
    if (!STRAPI_URL) {
        console.error("Strapi API URL environment variable is not configured.");
        return null;
    }
    try {
        const fullUrl = `${STRAPI_URL}/about-page`;
        console.log("Fetching About Page from:", fullUrl); // FIXME: Optional: for debugging

        const res = await fetch(fullUrl, { cache: 'no-store' }); // Fetch fresh data in dev

        if (!res.ok) {
            console.error(`Failed to fetch About Page: ${res.status} ${res.statusText}`);
            return null;
        }

        const jsonResponse: StrapiAboutPageResponse = await res.json();

        // Extract the data object which contains the attributes directly for single types
        const pageData = jsonResponse.data ?? null;
        console.log("Fetched About Page data:", pageData ? "[Data Object]" : pageData); // FIXME: Optional: for debugging

        return pageData;

    } catch (error) {
        console.error("Error fetching About Page:", error);
        return null;
    }
}

// Helper function to extract simple text from Strapi's body blocks
const extractTextFromBody = (bodyBlocks: AboutPageData['body']): string => {
    if (!bodyBlocks) return "";
    return bodyBlocks
        .map(block => block.children.map(child => child.text).join(''))
        .join('\n\n'); // Add double newline for paragraph spacing
};

export default async function AboutPage() {
    const content = await getAboutContent();

    if (!content) {
        return <main className="max-w-3xl mx-auto p-4 text-center text-red-500">Error loading content.</main>;
    }

    const bodyText = extractTextFromBody(content.body);

    return (
        <main className="max-w-3xl mx-auto p-4">
            <h1 className="text-3xl font-bold mb-6">{content.title || 'About Me'}</h1>

            {bodyText ? (
                <div className="prose dark:prose-invert max-w-none space-y-4 whitespace-pre-wrap">
                    {bodyText}
                    {/* Note: This won't render bold/italic etc. Use @strapi/blocks-react-renderer for full formatting later */}
                </div>
            ) : (
                <p>About content coming soon...</p>
            )}
        </main>
    );
}