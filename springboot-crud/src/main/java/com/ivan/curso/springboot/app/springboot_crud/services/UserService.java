package com.ivan.curso.springboot.app.springboot_crud.services;

import java.util.*;

import com.ivan.curso.springboot.app.springboot_crud.entities.User;

public interface UserService {

    List<User> findAll();

    User save(User user);
}
