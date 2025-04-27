package com.viatkin.portfolio_backend.content.controller;


import com.viatkin.portfolio_backend.content.dto.AboutContentDto;
import com.viatkin.portfolio_backend.content.service.AboutContentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/content")
public class AdminContentController {

    private final AboutContentService aboutContentService;

    public AdminContentController(AboutContentService aboutContentService) {
        this.aboutContentService = aboutContentService;
    }

    @GetMapping("/about")
    public ResponseEntity<AboutContentDto> getAboutContentForAdmin() {
        return ResponseEntity.ok(aboutContentService.getAboutContent());
    }

    @PutMapping("/about")
    public ResponseEntity<AboutContentDto> updateAboutContent(@Valid @RequestBody AboutContentDto dto) {
        AboutContentDto updatedContent = aboutContentService.updateAboutContent(dto);
        return ResponseEntity.ok(updatedContent);
    }
}