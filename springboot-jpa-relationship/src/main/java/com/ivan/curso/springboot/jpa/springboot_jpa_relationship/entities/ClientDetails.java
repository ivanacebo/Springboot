package com.ivan.curso.springboot.jpa.springboot_jpa_relationship.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "client_details")
public class ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Boolean premium;
    private Integer point;

    public ClientDetails() {
    }
    public ClientDetails(Boolean premium, Integer point) {
        this.premium = premium;
        this.point = point;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Boolean getPremium() {
        return premium;
    }
    public void setPremium(Boolean premium) {
        this.premium = premium;
    }
    public Integer getPoint() {
        return point;
    }
    public void setPoint(Integer point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "{id=" + id +
            ", premium=" + premium +
            ", point=" + point +
            "}";
    }
    
}
