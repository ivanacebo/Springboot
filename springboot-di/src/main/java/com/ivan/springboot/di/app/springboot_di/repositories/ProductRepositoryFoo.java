package com.ivan.springboot.di.app.springboot_di.repositories;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ivan.springboot.di.app.springboot_di.models.Product;

@Repository
public class ProductRepositoryFoo implements IProductRepository{

    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Monitor Asus 27", 600L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(id, "Monitor Asus 27", 600L);
    }


}
