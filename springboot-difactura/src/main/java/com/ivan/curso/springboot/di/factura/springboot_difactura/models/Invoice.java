package com.ivan.curso.springboot.di.factura.springboot_difactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@ApplicationScope
@JsonIgnoreProperties({"targetSourse", "advisors"})
public class Invoice {

    @Autowired
    private Client client;

    @Value("${invoice.description.office}")
    private String description;

    @Autowired
    private List<Item> items;

    @PostConstruct
    public void init() {
        System.out.println("Creando el componente de la factura");
        client.setName(client.getName().concat("Acebo"));
        description.concat("del cliente" )
        .concat(client.getName().concat(" ").concat(client.getLastName()));
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destruyendo el componente o bean de invoice");
    }

    public Invoice() {
    }

    public Invoice(Client client, String description, List<Item> items) {
        this.client = client;
        this.description = description;
        this.items = items;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getTotal() {
        //int total = 0;
        //for (Item item : items) {
            //total += item.getImporte();
        //}
        return items.stream().mapToInt(Item::getImporte).sum();
    }

}
