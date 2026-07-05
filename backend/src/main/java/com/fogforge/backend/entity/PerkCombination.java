package com.fogforge.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name= "perk_combinations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerkCombination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "perk_combination_perks",
            joinColumns = @JoinColumn(name = "combination_id"),
            inverseJoinColumns = @JoinColumn(name = "perk_id")
    )
    private Set<Perk> perks;

    @Column(nullable = false, unique = true)
    private String signature;

    private LocalDateTime drawnAt;
}
