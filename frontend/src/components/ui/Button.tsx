import React from 'react';
import Link from 'next/link';
import { clsx } from 'clsx';
import { twMerge } from 'tailwind-merge';

type ButtonVariant = 'primary' | 'secondary' | 'ghost';
type ButtonSize = 'sm' | 'md' | 'lg';

// Define component props
interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
    href?: string;
    variant?: ButtonVariant;
    size?: ButtonSize;
    className?: string;
    children: React.ReactNode;
    download?: boolean | string;
    target?: string;
    rel?: string;
}

const Button: React.FC<ButtonProps> = ({
                                           href,
                                           variant = 'primary',
                                           size = 'md',
                                           className,
                                           children,
                                           type = 'button',
                                           download,
                                           target,
                                           rel,
                                           ...props
                                       }) => {
    // Base classes common to all buttons
    const baseClasses = 'inline-flex items-center justify-center font-bold rounded focus:outline-none focus:ring-2 focus:ring-offset-2 transition duration-200 ease-in-out disabled:opacity-50 disabled:cursor-not-allowed';

    // Classes specific to variants
    const variantClasses: Record<ButtonVariant, string> = {
        primary: 'bg-blue-600 hover:bg-blue-700 text-white focus:ring-blue-500',
        secondary: 'bg-gray-600 hover:bg-gray-700 text-white focus:ring-gray-500',
        ghost: 'bg-transparent hover:bg-gray-200 dark:hover:bg-gray-700 text-current focus:ring-gray-500',
    };

    const sizeClasses: Record<ButtonSize, string> = {
        sm: 'py-1 px-3 text-sm',
        md: 'py-2 px-4',
        lg: 'py-3 px-6 text-lg',
    };

    const mergedClasses = twMerge(
        clsx(
            baseClasses,
            variantClasses[variant],
            sizeClasses[size],
            className
        )
    );

    if (href) {
        return (
            <Link
                href={href}
                className={mergedClasses}
                download={download}
                target={target}
                rel={rel}
                {...(props as React.AnchorHTMLAttributes<HTMLAnchorElement>)}
            >
                {children}
            </Link>
        );
    }

    return (
        <button type={type} className={mergedClasses} {...props}>
            {children}
        </button>
    );
};

export default Button;