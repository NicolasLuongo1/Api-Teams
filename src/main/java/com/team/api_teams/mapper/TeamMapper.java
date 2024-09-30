package com.team.api_teams.mapper;

import com.team.api_teams.domain.Team;
import com.team.api_teams.dto.TeamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    List<TeamDTO> toTeamsDTO(List<Team> teams);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "league", target = "league")
    @Mapping(source = "country", target = "country")
    Team toTeam(TeamDTO teamDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "league", target = "league")
    @Mapping(source = "country", target = "country")
    TeamDTO toTeamDTO(Team team);
}