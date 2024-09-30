package com.team.api_teams.service;

import com.team.api_teams.dao.UserRepository;
import com.team.api_teams.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    public String findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(User::getPassword)
                .orElse(null);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
