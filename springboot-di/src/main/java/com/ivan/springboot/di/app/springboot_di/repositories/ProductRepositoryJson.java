package com.ivan.springboot.di.app.springboot_di.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivan.springboot.di.app.springboot_di.models.Product;

public class ProductRepositoryJson implements IProductRepository {

    private List<Product> list;

    public ProductRepositoryJson() {
        ClassPathResource resource = new ClassPathResource("product.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            list = Arrays.asList(objectMapper.readValue(resource.getInputStream(), Product[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product findById(Long id) {
        return list.stream()
        .filter(p -> p.getId().equals(id))
        .findFirst().orElse(null);
    }

}
