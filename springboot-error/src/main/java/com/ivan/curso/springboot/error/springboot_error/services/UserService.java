package com.ivan.curso.springboot.error.springboot_error.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivan.curso.springboot.error.springboot_error.models.domain.User;

@Service
public class UserService implements IUserService{

    @Autowired
    private List<User> users;

    @Override
    public Optional<User> findById(Long id) {
        return users.stream()
        .filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> findAll() {

        return users;
    }


}
