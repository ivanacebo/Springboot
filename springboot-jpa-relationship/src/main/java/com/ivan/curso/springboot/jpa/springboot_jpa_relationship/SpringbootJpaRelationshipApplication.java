package com.ivan.curso.springboot.jpa.springboot_jpa_relationship;

import java.util.Optional;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.curso.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.ivan.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.ivan.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.ivan.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.ivan.curso.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run (String... args) throws Exception {
		removeAddress();
	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Ramon", "Perez");

		Address address = new Address("El Prado", 1);
		Address address2 = new Address("Avenida Castilla", 2);

		client.getAddresses().add(address);
		client.getAddresses().add(address2);
		clientRepository.save(client);

		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findById(6L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address);
			clientRepository.save(c);
			System.out.println(c);
		});
	}

	// Crear cliente y añadir direcciones a un determinado cliente
	@Transactional
	public void oneToManyFindById() {
		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(client -> {
			Address address = new Address("Mont Blanc", 12);
			Address address2 = new Address("Avenida Moral", 21);

			client.setAddresses(Arrays.asList(address, address2));

			clientRepository.save(client);
			System.out.println(client);
		});
	}

	// Crear cliente y añadir direcciones
	@Transactional
	public void oneToMany() {
		Client client = new Client("Ramon", "Perez");

		Address address = new Address("El Prado", 1);
		Address address2 = new Address("Avenida Castilla", 2);

		client.getAddresses().add(address);
		client.getAddresses().add(address2);
		clientRepository.save(client);

		System.out.println(client);
	}

	// Crear cliente y añadir factura
	@Transactional
	public void manyToOne() {
		
		Client client = new Client("Ramon", "Perez");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	// Crear una factura y añadirle un determinado cliente
	@Transactional
	public void manyToOneFindByIdClient() {
		
		Optional<Client> optionalClient = clientRepository.findById(1L);

		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();

			Invoice invoice = new Invoice("Compras de oficina", 2000L);
			invoice.setClient(client);
			invoiceRepository.save(invoice);
			System.out.println(invoice);
		}
	}
}
