package com.ivan.curso.springboot.app.springboot_crud.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ivan.curso.springboot.app.springboot_crud.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
