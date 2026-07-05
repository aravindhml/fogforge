package com.fogforge.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "perks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Perk {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String chapter;
    private String iconUrl;
}
