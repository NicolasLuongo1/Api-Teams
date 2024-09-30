package com.team.api_teams.dao;

import com.team.api_teams.domain.Team;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository {
    Optional<List<Team>> findByName(String name);

    List<Team> findAll();

    Optional<Team> findById(int id);

    Team save(Team team);

    void deleteById(int id);
}
