package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import java.util.Optional;

@Configuration
@EnableMongoAuditing
public class DatabaseAuditingConfiguration implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional.of("user.demo@test.it");
    }
}