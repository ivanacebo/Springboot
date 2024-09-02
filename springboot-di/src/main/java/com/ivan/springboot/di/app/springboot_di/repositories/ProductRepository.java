package com.ivan.springboot.di.app.springboot_di.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
//import org.springframework.web.context.annotation.RequestScope;
//import org.springframework.web.context.annotation.SessionScope;

import com.ivan.springboot.di.app.springboot_di.models.Product;

//@RequestScope  por usuario
//@SessionScope por sesion
@Primary
@Repository("/productList")
public class ProductRepository implements IProductRepository{

    private List<Product> products;

    public ProductRepository() {
        this.products = Arrays.asList(
            new Product (1L, "Memoria Corsair 32", 1000L),
            new Product (2L, "Cpu Intel Core i9", 850L),
            new Product (3L, "Teclado Razer Mini 60%", 150L),
            new Product (4L, "Motherboard Gigabyte Aorus", 500L)
        );
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById (Long id){
        return products.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst().orElse(null);
    }
}
