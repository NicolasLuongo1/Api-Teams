package com.team.api_teams.dao;

import com.team.api_teams.domain.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Primary
public class H2DbTeamsRepository implements TeamRepository {

    private TeamJPARepository teamJPARepository;

    @Override
    public Optional<List<Team>> findByName(String name) {
        return teamJPARepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Team> findAll() {
        return teamJPARepository.findAll();
    }

    @Override
    public Optional<Team> findById(int id) {
        return teamJPARepository.findById(id);
    }

    @Override
    public Team save(Team team) {
        return teamJPARepository.save(team);
    }

    @Override
    public void deleteById(int id) {
        teamJPARepository.deleteById(id);
    }

    @Autowired
    public void setTeamJPARepository(TeamJPARepository teamJPARepository) {
        this.teamJPARepository = teamJPARepository;
    }
}
