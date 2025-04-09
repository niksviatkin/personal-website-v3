export interface Project {
  id: number;
  title: string;
  description: string | null;
  imageUrl: string | null;
  repoUrl: string | null;
  liveUrl: string | null;
  technologies: string | null;
}
