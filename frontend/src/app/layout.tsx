import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import './globals.css';
import React from 'react';
import StyledLink from '@/components/ui/StyledLink';

const inter = Inter({ subsets: ['latin'] });

export const metadata: Metadata = {
  title: 'Nikita Viatkin - Portfolio',
  description: 'Personal portfolio website',
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${inter.className} bg-gray-100 text-gray-900 dark:bg-gray-900 dark:text-gray-100`}
      >
        <header className="bg-white shadow-md dark:bg-gray-800">
          <nav className="container mx-auto flex items-center justify-between px-4 py-3">
            {/* ... TODO: Logo Link ... */}
            <div className="space-x-4">
              <StyledLink href="/" variant="nav">
                Home
              </StyledLink>
              <StyledLink href="/about" variant="nav">
                About
              </StyledLink>
              <StyledLink href="/resume" variant="nav">
                Resume
              </StyledLink>
              <StyledLink href="/projects" variant="nav">
                Projects
              </StyledLink>
            </div>
          </nav>
        </header>

        {/* Page Content */}
        <div className="container mx-auto px-4 py-6">{children}</div>

        {/* Simple Footer (Optional) */}
        <footer className="mt-8 border-t py-4 text-center text-sm text-gray-500 dark:border-gray-700 dark:text-gray-400">
          © {new Date().getFullYear()} Nikita Viatkin. All rights reserved.
          {/* TODO: Add social links here later? */}
        </footer>
      </body>
    </html>
  );
}
