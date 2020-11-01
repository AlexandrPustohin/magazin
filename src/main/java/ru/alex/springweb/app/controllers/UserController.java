package ru.alex.springweb.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alex.springweb.app.entities.Authority;
import ru.alex.springweb.app.entities.User;
import ru.alex.springweb.app.services.AuthorityService;
import ru.alex.springweb.app.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private AuthorityService authorityService;
    @Autowired
    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")//добавляем (регим) пользователя
    public String addUser(@ModelAttribute User user,
                          Principal principal, Model model) {
        List<User> userList = userService.getAllUser();

        for(User user1:userList){
            if(user1.getUsername().equalsIgnoreCase(user.getUsername())){
                model.addAttribute("isUser", true);
                return "reg";
            }
        }
        user.setPassword("{noop}"+user.getPassword());
        userService.save(user);
        authorityService.save(user);
        return "login";
    }
}
