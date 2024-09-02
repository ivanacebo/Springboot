package com.ivan.springboot.webapp.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.ivan.springboot.webapp.models.User;

@Controller
public class UserController {

    User user = new User("Iván", "Acebo González");

    @GetMapping("/details")
    public String details(Model model) {

        model.addAttribute("title", "Hola mundo spring boot");
        model.addAttribute("user", user);

        return "details";
    }

    @GetMapping("/list")
    public String lista(ModelMap model) {
        List<User> users = Arrays.asList(
                new User("Pepa", "Gonzlez"),
                new User("Iván", "Acebo", "ivan@correo.com"),
                new User("Leonardo", "Cachopo"));

        model.addAttribute("title", "Listado de usuarios");
        model.addAttribute("users", users);
        return "list";
    }
}
