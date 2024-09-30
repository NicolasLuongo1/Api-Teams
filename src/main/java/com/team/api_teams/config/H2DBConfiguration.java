package com.team.api_teams.config;

import com.team.api_teams.dao.TeamRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = TeamRepository.class)
public class H2DBConfiguration {
}