package com.ivan.springboot.webapp.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.springboot.webapp.DTO.UserDTO;
import com.ivan.springboot.webapp.models.User;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api")
public class UserRestController {

    User user = new User("Iván", "Acebo González");
    UserDTO userDTO = new UserDTO("Hola mundo con Spring boot", user);

    @GetMapping("/details")
    public Map<String, Object> details() {
        Map<String, Object> body = new HashMap<>();

        body.put("title", "Hola Mundo desde Spring");
        body.put("user", user);

        return body;
    }

    @GetMapping("/details-dto")
    public UserDTO detailsDto() {
        return userDTO;
    }

    @GetMapping("/list")
    public List<User> lista() {
        User user = new User("Iván", "Acebo González");
        User user1 = new User("Tomás", "Acebo González");
        User user2 = new User("Lina", "Acebo González");

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        users.add(user2);

        List<User> users1 = Arrays.asList(user, user1,user2);

        return users;
    }

}
