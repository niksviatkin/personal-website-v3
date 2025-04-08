package com.viatkin.portfolio_backend.skill.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillResponse {
    private Long id;
    private String name;
    private String iconIdentifier;

}