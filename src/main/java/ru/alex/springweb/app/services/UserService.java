package ru.alex.springweb.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.springweb.app.entities.Authority;
import ru.alex.springweb.app.entities.Product;
import ru.alex.springweb.app.entities.User;
import ru.alex.springweb.app.repositories.AuthorityRepository;
import ru.alex.springweb.app.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private AuthorityService authorityService;
    @Autowired
    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findByUsername(String username) {
        return userRepository.findById(username).get();
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void save(User user) {
        user.setEnabled(true);
        User newUser = userRepository.save(user);
        authorityService.save(user);



    }
}