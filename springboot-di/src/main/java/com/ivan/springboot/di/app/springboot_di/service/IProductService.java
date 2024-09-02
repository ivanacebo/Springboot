package com.ivan.springboot.di.app.springboot_di.service;

import java.util.List;

import com.ivan.springboot.di.app.springboot_di.models.Product;

public interface IProductService {

    List<Product> findAll();

    Product findById(Long id);
}
