package com.viatkin.portfolio_backend.content.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AboutContentDto {
    @Size(max = 255)
    private String title;

    @NotBlank(message = "About content body cannot be blank")
    private String body;
}
