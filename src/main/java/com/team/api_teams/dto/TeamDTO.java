package com.team.api_teams.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO {

    private int id;

    @NotBlank(message = "The name cannot be null or empty")
    private String name;

    @NotBlank(message = "The league cannot be null or empty")
    private String league;

    @NotBlank(message = "The country cannot be null or empty")
    private String country;

}