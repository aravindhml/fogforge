package com.fogforge.backend.service;

import com.fogforge.backend.dto.PerkCombinationResponse;
import com.fogforge.backend.dto.PerkCombinationStatsResponse;
import com.fogforge.backend.entity.Perk;
import com.fogforge.backend.entity.PerkCombination;
import com.fogforge.backend.entity.Role;
import com.fogforge.backend.exception.InsufficientPerksException;
import com.fogforge.backend.exception.NoUniqueCombinationException;
import com.fogforge.backend.repository.PerkCombinationRepository;
import com.fogforge.backend.repository.PerkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerkCombinationServiceImpl implements PerkCombinationService{
    private static final int COMBINATION_SIZE = 4;
    private static final int MAX_ATTEMPTS = 50;

    private final PerkRepository perkRepository;
    private final PerkCombinationRepository perkCombinationRepository;

    @Override
    @Transactional
    public PerkCombinationResponse drawRandomCombination(Role role) {
        List<Perk> pool = perkRepository.findByRole(role);

        if (pool.size() < COMBINATION_SIZE) {
            throw new InsufficientPerksException(
                    "Not enough " + role + " perks to form a combination of " + COMBINATION_SIZE);
        }

        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            List<Perk> shuffled = new ArrayList<>(pool);
            Collections.shuffle(shuffled, ThreadLocalRandom.current());
            Set<Perk> candidate = new HashSet<>(shuffled.subList(0, COMBINATION_SIZE));
            String signature = computeSignature(candidate);

            if (!perkCombinationRepository.existsBySignature(signature)) {
                PerkCombination combo = PerkCombination.builder()
                        .role(role)
                        .perks(candidate)
                        .signature(signature)
                        .drawnAt(LocalDateTime.now())
                        .build();
                perkCombinationRepository.save(combo);
                return PerkCombinationResponse.from(combo);
            }
        }

        throw new NoUniqueCombinationException(
                "Could not find a new " + role + " combination after " + MAX_ATTEMPTS + " attempts.");
    }

    @Override
    @Transactional(readOnly = true)
    public List<PerkCombinationResponse> getDrawnCombinations(Role role) {
        return perkCombinationRepository.findByRole(role).stream()
                .map(PerkCombinationResponse::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PerkCombinationStatsResponse getStats(Role role) {
        long drawnCount = perkCombinationRepository.findByRole(role).size();
        long poolSize = perkRepository.findByRole(role).size();
        return new PerkCombinationStatsResponse(drawnCount, nCr(poolSize, COMBINATION_SIZE));
    }

    private String computeSignature(Set<Perk> perks) {
        return perks.stream()
                .map(Perk::getId)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    private long nCr(long n, long r) {
        if (r > n) return 0;
        long result = 1;
        for (int i = 0; i < r; i++) {
            result = result * (n - i) / (i + 1);
        }
        return result;
    }
}
