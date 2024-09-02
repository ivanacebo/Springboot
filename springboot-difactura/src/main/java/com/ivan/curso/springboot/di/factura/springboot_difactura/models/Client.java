package com.ivan.curso.springboot.di.factura.springboot_difactura.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@RequestScope
@JsonIgnoreProperties({"targetSourse", "advisors"})
public class Client {

    @Value("${client.name}")
    private String name;
    @Value("${client.lastName}")
    private String lastName;

    public Client() {
    }

    public Client(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
