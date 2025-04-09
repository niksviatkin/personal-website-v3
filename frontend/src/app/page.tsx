import Button from '@/components/ui/Button';

export default function HomePage() {
  return (
    <main className="flex min-h-[70vh] flex-col items-center justify-center p-4 text-center">
      {' '}
      {/* Adjust min-height as needed */}
      {/* TODO: Optional: Add your photo here */}
      {/* <img src="/your-photo.jpg" alt="Your Name" className="w-32 h-32 rounded-full mb-4" /> */}
      <h1 className="mb-3 text-4xl font-bold md:text-5xl">Nikita Viatkin</h1>
      <p className="mb-6 text-xl text-gray-700 md:text-2xl dark:text-gray-300">
        Software Developer | Building Full-Stack Solutions
      </p>
      <p className="mx-auto mb-8 max-w-2xl text-gray-600 dark:text-gray-400">
        Welcome to my personal portfolio. I specialize in creating robust
        backend systems with Java/Spring and interactive frontends with
        React/Next.js. Explore my projects and resume to learn more!
      </p>
      <div className="flex flex-wrap justify-center gap-4">
        <Button href="/projects" variant="primary">
          View Projects
        </Button>
        <Button href="/resume" variant="secondary">
          View Resume
        </Button>
      </div>
    </main>
  );
}
