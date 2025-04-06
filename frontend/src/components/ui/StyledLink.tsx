import React from 'react';
import Link, { LinkProps } from 'next/link';
import { clsx } from 'clsx';
import { twMerge } from 'tailwind-merge';

type LinkVariant = 'default' | 'nav' | 'primaryAction' | 'secondaryAction';

interface StyledLinkProps extends Omit<React.AnchorHTMLAttributes<HTMLAnchorElement>, keyof LinkProps>, Omit<LinkProps, 'className'> {
    variant?: LinkVariant;
    className?: string;
    children: React.ReactNode;
}

const StyledLink: React.FC<StyledLinkProps> = ({
                                                   href,
                                                   variant = 'default',
                                                   className,
                                                   children,
                                                   ...props
                                               }) => {

    const baseClasses = 'focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 rounded';

    const variantClasses: Record<LinkVariant, string> = {
        default: 'text-blue-600 hover:text-blue-800 dark:text-blue-400 dark:hover:text-blue-300 underline hover:no-underline',
        nav: 'hover:text-blue-600 dark:hover:text-blue-400 transition-colors duration-150',
        primaryAction: 'text-blue-600 hover:text-blue-800 dark:text-blue-400 dark:hover:text-blue-300 text-sm transition-colors',
        secondaryAction: 'text-green-600 hover:text-green-800 dark:text-green-400 dark:hover:text-green-300 text-sm transition-colors',
    };

    const mergedClasses = twMerge(
        clsx(
            baseClasses,
            variantClasses[variant],
            className
        )
    );

    return (
        <Link href={href} className={mergedClasses} {...props}>
            {children}
        </Link>
    );
};

export default StyledLink;