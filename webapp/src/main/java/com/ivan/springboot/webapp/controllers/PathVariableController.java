package com.ivan.springboot.webapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.springboot.webapp.DTO.ParamDto;
import com.ivan.springboot.webapp.models.User;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    @Value("${config.userName}")
    private String userName;

    @Value("${config.message}")
    private String message;

    @Value("${config.listOfValues}")
    private String[] listOfValues;
    
    @Value("${config.code}")
    private Integer code;

    @GetMapping("baz/{message}")
    public ParamDto baz(@PathVariable String message) {

        ParamDto param = new ParamDto();
        param.setMessagge(message);
        return param;
    }

    @GetMapping("mix/{producto}/{codigo}")
    public Map<String, Object> mix(@PathVariable String producto, @PathVariable Integer codigo) {
        Map<String, Object> body = new HashMap<>();
        body.put("producto", producto);
        body.put("codigo", codigo);

        return body;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        user.setName(user.getName().toUpperCase());
        return user;
    }

    @GetMapping("/values")
    public Map<String, Object> values() {

        Map<String, Object> json = new HashMap<>();
        json.put("userName", userName);
        json.put("message", message);
        json.put("listOfValues", listOfValues);
        json.put("code", code);

        return json;
    }
}
