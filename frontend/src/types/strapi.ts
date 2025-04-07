// Represents one block in Strapi's Rich Text JSON output
// (Can be expanded with more types like list, heading, etc. if needed)
export interface StrapiRichTextBlock {
    type: string;
    children: { type: string; text: string; bold?: boolean; italic?: boolean; /* etc. */ }[];
}

// --- About Page ---
// Represents the structure inside the 'data' object for the About Page (Single Type)
export interface AboutPageData {
    id: number;
    title: string | null;
    body: StrapiRichTextBlock[] | null; // Body is an array of blocks
    createdAt: string;
    updatedAt: string;
    publishedAt: string | null;
}

// Represents the overall structure of the API response for the About Page
export interface StrapiAboutPageResponse {
    data: AboutPageData | null; // Single types might be null if unpublished
    meta?: object; // Optional meta object
}


// --- Resume Related ---

// Data structure for individual Experience Items (as returned in the array)
export interface ExperienceItemData {
    id: number;
    title: string;
    company: string;
    location: string | null;
    years: string;
    description: StrapiRichTextBlock[] | null; // Use block type
    // Add createdAt, updatedAt etc. if needed from JSON
}

// Data structure for individual Education Items (as returned in the array)
export interface EducationItemData {
    id: number;
    degree: string;
    university: string;
    years: string | null;
    details: string | null;
    // Add createdAt, updatedAt etc. if needed from JSON
}

// Attributes for Experience Item (Collection Type)
export interface ExperienceItemAttributes {
    title: string;
    company: string;
    location: string | null;
    years: string;
    description: StrapiRichTextBlock[] | null; // Assuming Rich Text
    createdAt: string;
    updatedAt: string;
    publishedAt: string | null;
}

// Attributes for Education Item (Collection Type)
export interface EducationItemAttributes {
    degree: string;
    university: string;
    years: string | null;
    details: string | null; // Assuming Long Text (string)
    createdAt: string;
    updatedAt: string;
    publishedAt: string | null;
}

// Data structure for the object inside 'data' for the Resume response
// (Essentially the old ResumeAttributes + ID + flattened relations)
export interface ResumeData {
    id: number;
    summary: string | null;
    skills_languages: string | null;
    skills_frameworks: string | null;
    skills_databases: string | null;
    skills_tools: string | null;
    skills_other: string | null;
    // New Contact fields (use names matching Strapi API IDs)
    contact_name: string | null;
    contact_pageTitle: string | null;
    contact_location: string | null;
    contact_email: string | null;
    contact_linkedinUrl: string | null;
    contact_githubUrl: string | null;
    languages: string | null;
    interests: string | null;
    certifications: CertificationComponent[] | null; // Array of Certification components
    // Populated relations (keep as before)
    experience_items: ExperienceItemData[] | null;
    education_items: EducationItemData[] | null;
    // Strapi meta fields
    createdAt: string;
    updatedAt: string;
    publishedAt: string | null;
}

// Represents the overall structure of the API response for the Resume (Single Type)
export interface StrapiResumeResponse {
    data: ResumeData | null;
    meta?: object;
}


// Generic Project type (if needed elsewhere, though Projects come from Spring Boot API)
// Keep the existing Project type for the Spring Boot API data
// For now, keep the existing one from src/types/project.ts for the Spring Boot data:
/*
  export interface StrapiProjectType {
    id: number;
    title: string;
    description: string | null;
    imageUrl: string | null;
    repoUrl: string | null;
    liveUrl: string | null;
    technologies: string | null;
  }
*/

export interface CertificationComponent {
    id: number; // Components have an internal ID
    name: string;
    issuer: string | null;
    date: string | null;
    link: string | null;
}