package com.fogforge.backend.repository;

import com.fogforge.backend.entity.Perk;
import com.fogforge.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerkRepository extends JpaRepository<Perk,Long> {
    List<Perk> findByRole(Role role);
}
