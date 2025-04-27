package com.viatkin.portfolio_backend.content.service;

import com.viatkin.portfolio_backend.content.domain.AboutContent;
import com.viatkin.portfolio_backend.content.dto.AboutContentDto;
import com.viatkin.portfolio_backend.content.repository.AboutContentRepository;
import com.viatkin.portfolio_backend.error.ResourceNotFoundException; // Use your exception
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AboutContentService {

    private final AboutContentRepository aboutContentRepository;
    private final Long ABOUT_CONTENT_ID = 1L;

    public AboutContentService(AboutContentRepository aboutContentRepository) {
        this.aboutContentRepository = aboutContentRepository;
    }

    @Transactional(readOnly = true)
    public AboutContentDto getAboutContent() {
        AboutContent content = findAboutContentOrFail();
        return mapToDto(content);
    }

    @Transactional
    public AboutContentDto updateAboutContent(AboutContentDto dto) {
        AboutContent content = findAboutContentOrFail();

        content.setTitle(dto.getTitle());
        content.setBody(dto.getBody());

        AboutContent updatedContent = aboutContentRepository.save(content);
        return mapToDto(updatedContent);
    }

    private AboutContent findAboutContentOrFail() {
        return aboutContentRepository.findById(ABOUT_CONTENT_ID)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "About content not found with ID: " + ABOUT_CONTENT_ID + ". Please ensure it has been seeded."
                ));
    }

    private AboutContentDto mapToDto(AboutContent entity) {
        // Ensure AboutContentDto constructor matches or use setters
        return new AboutContentDto(entity.getTitle(), entity.getBody());
    }

    @Transactional
    public void initializeAboutContent(String defaultTitle, String defaultBody) {
        if (!aboutContentRepository.existsById(ABOUT_CONTENT_ID)) {
            AboutContent initialContent = new AboutContent(ABOUT_CONTENT_ID, defaultTitle, defaultBody);
            aboutContentRepository.save(initialContent);
        }
    }
}