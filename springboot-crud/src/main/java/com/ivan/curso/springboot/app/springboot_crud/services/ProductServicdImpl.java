package com.ivan.curso.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.curso.springboot.app.springboot_crud.entities.Product;
import com.ivan.curso.springboot.app.springboot_crud.repositories.ProductRepository;

@Service
public class ProductServicdImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOptionalDb = productRepository.findById(id);

        if (productOptionalDb.isPresent()) {
            Product productDb = productOptionalDb.orElseThrow();
            productDb.setName(product.getName());
            productDb.setPrice(product.getPrice());
            productDb.setDescription(product.getDescription());

            return Optional.of(productRepository.save(productDb));
        }
        return productOptionalDb;
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productOptionalDb = productRepository.findById(id);

        productOptionalDb.ifPresent(productDb -> {
            productRepository.delete(productDb);
        });
        return productOptionalDb;
    }

    

}
