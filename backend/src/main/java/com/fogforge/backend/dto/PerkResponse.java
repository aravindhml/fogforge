package com.fogforge.backend.dto;

import com.fogforge.backend.entity.Perk;
import com.fogforge.backend.entity.Role;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PerkResponse {

    Long id;
    String name;
    String description;
    Role role;
    String chapter;
    String iconUrl;

    public static PerkResponse from(Perk perk) {
        return new PerkResponse(
                perk.getId(),
                perk.getName(),
                perk.getDescription(),
                perk.getRole(),
                perk.getChapter(),
                perk.getIconUrl()
        );
    }
}