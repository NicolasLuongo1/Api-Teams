package com.team.api_teams.controller;

import com.team.api_teams.domain.Team;
import com.team.api_teams.dto.ResponseDTO;
import com.team.api_teams.dto.TeamDTO;
import com.team.api_teams.exception.CustomArgumentNotValidException;
import com.team.api_teams.exception.CustomDatabaseException;
import com.team.api_teams.exception.CustomNotFoundException;
import com.team.api_teams.mapper.TeamMapper;
import com.team.api_teams.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    private TeamService teamService;
    private TeamMapper teamMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeam(@PathVariable("id") int id) {
        try {
            Team team = teamService.findById(id);
            return ResponseEntity.ok(team);
        }  catch (CustomNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND.value()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getTeams() {
        List<Team> teams = teamService.findAll();
        List<TeamDTO> teamsDTO = teamMapper.toTeamsDTO(teams);
        return ResponseEntity.ok(teamsDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TeamDTO>> getTeamsByName(@RequestParam("name") String name) {
        List<Team> teamsByName = teamService.findByName(name);
        List<TeamDTO> teamTO = teamMapper.toTeamsDTO(teamsByName);
        return ResponseEntity.ok(teamTO);
    }

    @PostMapping
    public ResponseEntity<?> createTeam(@Valid @RequestBody TeamDTO teamDTO) {
        try {
            Team team = teamMapper.toTeam(teamDTO);
            Team savedTeam = teamService.save(team);
            TeamDTO savedTeamDTO = teamMapper.toTeamDTO(savedTeam);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTeamDTO);
        } catch (CustomDatabaseException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (CustomArgumentNotValidException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTeam(@Valid int id, @RequestBody TeamDTO teamDTO) {
        try {
            Team team = teamMapper.toTeam(teamDTO);
            team.setId(id);
            Team updatedTeam = teamService.update(team);
            TeamDTO updatedTeamDTO = teamMapper.toTeamDTO(updatedTeam);
            return ResponseEntity.ok(updatedTeamDTO);
        } catch (CustomNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") int id) {
        try {
            teamService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }  catch (CustomNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @Autowired
    public void setTeamMapper(TeamMapper teamMapper) {
        this.teamMapper = teamMapper;
    }
}