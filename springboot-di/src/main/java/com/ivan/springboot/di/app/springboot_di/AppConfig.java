package com.ivan.springboot.di.app.springboot_di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.ivan.springboot.di.app.springboot_di.repositories.IProductRepository;
import com.ivan.springboot.di.app.springboot_di.repositories.ProductRepositoryJson;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Bean
    IProductRepository productRepositoryJson() {
        return new ProductRepositoryJson();
    }
}
