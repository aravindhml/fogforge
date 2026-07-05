package com.fogforge.backend.repository;

import com.fogforge.backend.entity.PerkCombination;
import com.fogforge.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerkCombinationRepository extends JpaRepository<PerkCombination,Long> {

    boolean existsBySignature(String signature);

    List<PerkCombination> findByRole(Role role);

}
