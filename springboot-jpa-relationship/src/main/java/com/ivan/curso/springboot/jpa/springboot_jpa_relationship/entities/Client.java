package com.ivan.curso.springboot.jpa.springboot_jpa_relationship.entities;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private Set<Invoice> invoices;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
        name = "client_address", 
        joinColumns = @JoinColumn(name = "client_id"), 
        inverseJoinColumns = @JoinColumn(name = "address_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"address_id"}))
    private Set<Address> addresses;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private ClientDetails clientDetails;

    public Client() {
        addresses = new HashSet<>();
        invoices = new HashSet<>();
    }

    public Client(String name, String lastName) {
        this();
        this.name = name;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
        clientDetails.setClient(this);
    }

    public void removeClientDetails(ClientDetails clientDetails) {
        clientDetails.setClient(null);
        this.clientDetails = null;
    }

    public Client addInvoice(Invoice invoice) {
        invoices.add(invoice);
        invoice.setClient(this);
        return this;
    }

    @Override
    public String toString() {
        return "{id=" + id +
            ", name=" + name +
            ", lastName=" + lastName + 
            ", invoices=" + invoices +
            ", addresses=" + addresses +
            ", clientDetails=" + clientDetails +
            "}";
    }

}
