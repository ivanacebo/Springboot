package com.ivan.springboot.di.app.springboot_di.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ivan.springboot.di.app.springboot_di.models.Product;
import com.ivan.springboot.di.app.springboot_di.repositories.IProductRepository;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository repository;

    @Autowired
    private Environment environment;

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream()
        .map (p -> {
            Double priceTax = p.getPrice() * environment.getProperty("config.price.tax", Double.class);
            Product newProduct = (Product) p.clone();
            newProduct.setPrice(priceTax.longValue());
            return newProduct;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }
}
