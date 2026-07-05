package com.fogforge.backend.config;

import com.fogforge.backend.entity.Perk;
import com.fogforge.backend.entity.Role;
import com.fogforge.backend.repository.PerkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(PerkRepository perkRepository) {

        return args -> {

            if (perkRepository.count() == 0) {

                Perk perk = Perk.builder()
                        .name("Sprint Burst")
                        .description("When you start running, break into a sprint.")
                        .role(Role.SURVIVOR)
                        .chapter("Base Game")
                        .iconUrl("")
                        .build();

                perkRepository.save(perk);
            }

        };

    }

}