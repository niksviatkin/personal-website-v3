package com.viatkin.portfolio_backend.content.controller;


import com.viatkin.portfolio_backend.content.dto.AboutContentDto;
import com.viatkin.portfolio_backend.content.service.AboutContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/content") // Base path for public content
public class ContentController {

    private final AboutContentService aboutContentService;

    public ContentController(AboutContentService aboutContentService) {
        this.aboutContentService = aboutContentService;
    }

    @GetMapping("/about")
    public ResponseEntity<AboutContentDto> getAboutContent() {
        // TODO: Potentially add caching headers here later
        return ResponseEntity.ok(aboutContentService.getAboutContent());
    }
}