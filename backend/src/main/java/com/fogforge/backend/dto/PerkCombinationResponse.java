package com.fogforge.backend.dto;

import com.fogforge.backend.entity.PerkCombination;
import com.fogforge.backend.entity.Role;

import java.time.LocalDateTime;
import java.util.List;

public record PerkCombinationResponse(
        Long id,
        Role role,
        List<PerkResponse> perks,
        LocalDateTime drawnAt
) {
    public static PerkCombinationResponse from(PerkCombination combo) {
        return new PerkCombinationResponse(
                combo.getId(),
                combo.getRole(),
                combo.getPerks().stream().map(PerkResponse::from).toList(),
                combo.getDrawnAt()
        );
    }
}