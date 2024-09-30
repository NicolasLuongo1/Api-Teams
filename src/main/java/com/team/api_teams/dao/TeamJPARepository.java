package com.team.api_teams.dao;

import com.team.api_teams.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamJPARepository extends JpaRepository<Team, Integer> {
    Optional<List<Team>> findByNameContainingIgnoreCase(String name);
}
