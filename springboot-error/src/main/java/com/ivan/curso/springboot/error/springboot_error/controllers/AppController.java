package com.ivan.curso.springboot.error.springboot_error.controllers;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.curso.springboot.error.springboot_error.models.domain.User;
import com.ivan.curso.springboot.error.springboot_error.services.IUserService;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private IUserService service;

    @GetMapping
    public String index() {

        int value = Integer.parseInt("10x");
        System.out.println(value);
        return "Ok 200";

    }

    @GetMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        //User user = service.findById(id).orElseThrow(() -> new UserNotFoundException("Error el usuario no existe"));
        
        Optional<User> user = service.findById(id);
        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.orElseThrow);
       
    }
}
