package com.ivan.curso.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ivan.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.ivan.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.ivan.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;

import org.springframework.transaction.annotation.Transactional;


@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        update();
    }

	@Transactional(readOnly = true)
	public void whereIn() {
		System.out.println("Consulta where in");
		List<Person> persons = repository.getPersonsByIds();
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void subQueries() {
		System.out.println("Consulta por el nombre mas corto y su largo");
		List<Object[]> registers = repository.getShorterName();
		registers.forEach(reg -> {
			System.out.println("Name: " + reg[0] + " length: " + reg[1]);
		});

		System.out.println("Consulta por el id mas alto");
		Optional<Person> person = repository.getMaxId();
		person.ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void queriesFunctionalAggregation() {
		System.out.println("Consulta con el nombre y su largo");
		List<Object[]> regs = repository.getPersonNameLenght();
		regs.forEach(reg -> {
			System.out.println(reg[0] + " " + reg[1]);
		});
	}

	// CREAR NUEVOS REGISTROS
	@Transactional
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter programming language:");
        String programmingLanguage = scanner.nextLine();
        
        Person person = new Person(null, name, lastName, programmingLanguage);
        
        Person personNew = repository.save(person);

        System.out.println(personNew);
		scanner.close();

        repository.findById(personNew.getId()).ifPresent(System.out::println); 
    }

	// MODIFICAR REGISTROS EXISTENTES
	@Transactional
	public void update() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter id:");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);

		optionalPerson.ifPresent(person -> {
			System.out.println("Enter programming language:");
			String programmingLanguage = scanner.next();
			person.setProgrammingLanguage(programmingLanguage);
			repository.save(person);
			System.out.println(person);
			
		});
		scanner.close();

	}

	// ELIMINAR REGISTROS EXISTENTES
	@Transactional
	public void delete() {
		repository.findAll().forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter id to delete:");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(repository::delete, () -> System.out.println("Not found"));
		scanner.close();
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesBetween() {
		System.out.println("consulta por rangos");
		List<Person> persons = repository.findByIdBetween(2L, 5L);
		persons.forEach(System.out::println);

		persons = repository.findByNameBetween("J", "P");
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueryDistinct() {
		System.out.println("Consultas con nombre de personas");
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("Consultas con nombre de personas distintos");
		List<String> namesDistinct = repository.findAllNamesDistinct();
		namesDistinct.forEach(System.out::println);

		System.out.println("Consultas de lenguaje de programación distintos");
		List<String> languagesDistinct = repository.findAllProgrammingLanguagesDistinct();
		languagesDistinct.forEach(System.out::println);

		System.out.println("Cuenta de lenguajes de programación distintos");
		Long languagesDistinctCount = repository.findAllProgrammingLanguagesDistinctCount();
		System.out.println(languagesDistinctCount);
	}

	@Transactional(readOnly = true)
	public void personalizedQuery() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("============== Consulta el nombre por el id ==============");
		System.out.println("Enter id:");
		Long id = scanner.nextLong();

		String name = repository.getNameById(id);
		System.out.println(name);

		String fullName = repository.getFullName(id);
		System.out.println(fullName);
		scanner.close();
	}

	@Transactional(readOnly = true)
	public void personalizedQuery2() {
		System.out.println("============== Consulta por objeto persona y lenguaje de programación ==============");
		
		List<Object[]> personsRegs = repository.findMixPerson();

		personsRegs.forEach(reg -> {
			System.out.println("programmingLanguage: " + reg[1] + " name: " + reg[0]);
		});

		System.out.println("Consulta que devuelve un objeto entity de una instancia personalizada");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(person ->System.out.println(person));

		System.out.println("Consulta que devuelve objeto dto de una clase personalizada");
		List<PersonDto> personDto = repository.findAllPersonDto();
		personDto.forEach(System.out::println);

		System.out.println("Consulta que devuelve todos entre el 2 y el 5");
		List<Person> personsBetween = repository.findAllBetween();
		personsBetween.forEach(System.out::println);

		System.out.println("Consulta que devuelve todos entrela J y la P");
		List<Person> personNameBetween = repository.findAllByNameBetween();
		personNameBetween.forEach(System.out::println);
	}

	public void findOne() {
		Person person = null;
		Optional<Person> personOptional = repository.findById(1L);
		if (personOptional.isPresent()) {
			person = personOptional.get();
			System.out.println(person);
		} else {
			System.out.println("Not found");
		}
	}

	public void findOneOptional() {
		//repository.findById(1L).ifPresent(System.out::println);
		repository.findByNameContaining("hn").ifPresent(System.out::println);
	}

	public void list() {
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguage("Java");
		persons.stream()
		.forEach(person -> System.out.println(person.toString()));

		List<Person> persons1 = (List<Person>) repository.buscarByProgrammingLanguageQuery("Python");
		persons1.stream()
		.forEach(person -> System.out.println(person.toString()));

		List<Object[]> personsValue = repository.obtenerPersonData();
		personsValue.stream()
		.forEach(person -> { System.out.println(person[0] + " es experto en " + person[1]); });
	}
}
