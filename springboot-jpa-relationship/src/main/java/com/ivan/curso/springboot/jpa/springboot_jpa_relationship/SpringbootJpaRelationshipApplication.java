package com.ivan.curso.springboot.jpa.springboot_jpa_relationship;

import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

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
		removeInvoiceBidireccionalfindById();
	}

	// Eliminar una factura por id de un determinado cliente por id
	@Transactional
	public void removeInvoiceBidireccionalfindById() {
		Optional<Client> optionalClient = clientRepository.findOne(2L);

		optionalClient.ifPresent(client -> {
			Invoice invoice = new Invoice("Compra ordenador", 5000L);
			Invoice invoice2 = new Invoice("Compra mesa", 8000L);

			client.addInvoice(invoice).addInvoice(invoice2);
			clientRepository.save(client);
			System.out.println("Factura del cliente:");
			System.out.println(client);
		});

		Optional<Client> optionalClientDb = clientRepository.findOne(2L);

		optionalClientDb.ifPresent(client -> {
			Optional<Invoice> optionalInvoice = invoiceRepository.findById(2L);
			optionalInvoice.ifPresent(invoice -> {
				client.getInvoices().remove(invoice);
				invoice.setClient(null);

				clientRepository.save(client);
				System.out.println(client);
			});
		});
	}

	@Transactional
	public void oneToManyInvoiceBidireccionalfindById() {
		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice = new Invoice("Compra ordenador", 3000L);
			Invoice invoice2 = new Invoice("Compra tableta", 100L);

			client.addInvoice(invoice).addInvoice(invoice2);

			clientRepository.save(client);

			System.out.println("Factura del cliente:");
			System.out.println(client);
		});
		
	}

	// Bidireccional manyToOne y oneToMany (Invoice), (Client).
	@Transactional
	public void oneToManyInvoiceBidireccional() {
		Client client = new Client("Fran", "Moras");

		Invoice invoice = new Invoice("Compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("Compras de la oficina", 8000L);

		client.addInvoice(invoice).addInvoice(invoice2);

		clientRepository.save(client);

		System.out.println("Factura del cliente:");
		System.out.println(client);
		
	}

	// Eliminar una dirección
	@Transactional
	public void removeAdressFindById() {
		Optional<Client> optionalClient = clientRepository.findById(6L);
		optionalClient.ifPresent(client -> {
			Address address = new Address("El verjel", 12);
			Address address2 = new Address("Vasco de Gama", 21);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address);
			addresses.add(address2);
			client.setAddresses(addresses);
			clientRepository.save(client);
			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(6L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address);
				clientRepository.save(c);
				System.out.println(c);
			});
		});
	}

	// Eliminar una dirección
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

			Set<Address> addresses = new HashSet<>();
			addresses.add(address);
			addresses.add(address2);
			client.setAddresses(addresses);
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
