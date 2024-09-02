package com.ivan.curso.springboot.error.springboot_error.services;

import java.util.List;
import java.util.Optional;

import com.ivan.curso.springboot.error.springboot_error.models.domain.User;

public interface IUserService {

    Optional<User> findById(Long id);
    
    List<User> findAll();

}
