package com.ivan.curso.springboot.app.springboot_crud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ivan.curso.springboot.app.springboot_crud.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query
    Optional<Role> findByName(String name);
}
