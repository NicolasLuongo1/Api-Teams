package com.team.api_teams.controller;

import com.team.api_teams.dao.TeamRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TeamControllerTest {

    public static final String GET_TEAM_URL = "/team/{id}";
    public static final String GET_ALL_TEAM_URL = "/team";
    public static final String GET_ALL_TEAM_BY_NAME_URL = "/team/search?name={name}";
    public static final String ADD_TEAM_URL = "/team";
    public static final String UPDATE_TEAM_URL = "/team/{id}";
    public static final String DELETE_TEAM_URL = "/team/{id}";

    private MockMvc mockMvc;

    private TeamRepository teamRepository;

    @Test
    @WithMockUser
    void shouldReturnTeamWhenIdIs9() throws Exception {
        val id = 9;

        mockMvc.perform(get(GET_TEAM_URL, id)).andExpect(status().isOk())
                .andExpect(content().json("""
                        {"id": 9, "name": "Paris Saint-Germain", "league":"Ligue 1", "country":"Francia"}
                """));
    }

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
}
