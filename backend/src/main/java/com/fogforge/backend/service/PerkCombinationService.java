package com.fogforge.backend.service;

import com.fogforge.backend.dto.PerkCombinationResponse;
import com.fogforge.backend.dto.PerkCombinationStatsResponse;
import com.fogforge.backend.entity.Role;

import java.util.List;

public interface PerkCombinationService {

    PerkCombinationResponse drawRandomCombination(Role role);

    List<PerkCombinationResponse> getDrawnCombinations(Role role);

    PerkCombinationStatsResponse getStats(Role role);
}
