import Button from '@/components/ui/Button';

export default function HomePage() {
  return (
      <main className="flex flex-col items-center justify-center min-h-[70vh] text-center p-4"> {/* Adjust min-height as needed */}
        {/* TODO: Optional: Add your photo here */}
        {/* <img src="/your-photo.jpg" alt="Your Name" className="w-32 h-32 rounded-full mb-4" /> */}

        <h1 className="text-4xl md:text-5xl font-bold mb-3">
          Nikita Viatkin
        </h1>
        <p className="text-xl md:text-2xl text-gray-700 dark:text-gray-300 mb-6">
          Software Developer | Building Full-Stack Solutions
        </p>
        <p className="max-w-2xl mx-auto text-gray-600 dark:text-gray-400 mb-8">
          Welcome to my personal portfolio. I specialize in creating robust backend systems with Java/Spring and interactive frontends with React/Next.js. Explore my projects and resume to learn more!
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