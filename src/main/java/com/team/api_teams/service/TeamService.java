package com.team.api_teams.service;

import com.team.api_teams.dao.TeamRepository;
import com.team.api_teams.domain.Team;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private TeamRepository teamRepository;
    private static final String TEAM_NOT_FOUND = "No team found with %s: %s";

    public List<Team> findByName(String name) {
        return teamRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException(String.format(TEAM_NOT_FOUND, "name", name)));
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Team findById(int id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(TEAM_NOT_FOUND, "id", id)));
    }

    public Team save(Team team) {
        if (teamRepository.findById(team.getId()).isPresent()) {
            throw new DataIntegrityViolationException("team already exists with id: " + team.getId());
        }
        return teamRepository.save(team);
    }

    public Team update(Team team) {
        if (teamRepository.findById(team.getId()).isEmpty()) {
            throw new EntityNotFoundException(String.format(TEAM_NOT_FOUND, "id", team.getId()));
        }
        return teamRepository.save(team);
    }

    public void deleteById(int id) {
        if (teamRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(String.format(TEAM_NOT_FOUND, "id", id));
        }
        teamRepository.deleteById(id);
    }

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
}

