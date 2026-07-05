package com.fogforge.backend.controller;

import com.fogforge.backend.dto.PerkCombinationResponse;
import com.fogforge.backend.dto.PerkCombinationStatsResponse;
import com.fogforge.backend.entity.Role;
import com.fogforge.backend.service.PerkCombinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/perk-combination")
@RequiredArgsConstructor
public class PerkCombinationController
{

    private final PerkCombinationService perkCombinationService;

    @GetMapping("/random")
    public PerkCombinationResponse drawRandom(@RequestParam Role role) {
        return perkCombinationService.drawRandomCombination(role);
    }

    @GetMapping("/drawn")
    public List<PerkCombinationResponse> getDrawn(@RequestParam Role role) {
        return perkCombinationService.getDrawnCombinations(role);
    }

    @GetMapping("/stats")
    public PerkCombinationStatsResponse getStats(@RequestParam Role role) {
        return perkCombinationService.getStats(role);
    }
}
