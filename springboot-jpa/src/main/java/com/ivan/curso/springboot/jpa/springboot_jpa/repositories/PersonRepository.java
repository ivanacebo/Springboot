package com.ivan.curso.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ivan.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.ivan.curso.springboot.jpa.springboot_jpa.entities.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("select p from Person p where p.id in (1, 2, 5)")
    List<Person> getPersonsByIds();

    @Query("select p.name, length(p.name) from Person p where length(p.name) = (select min(length(p.name)) from Person p)")
    List<Object[]> getShorterName();

    @Query("select p from Person p where p.id = (select max(p.id) from Person p)")
    Optional<Person> getMaxId();

    @Query("select p.name, length(p.name) from Person p")
    List<Object[]> getPersonNameLenght();

    @Query("select p from Person p where p.name between ?1 and ?2")
    List<Person> findByNameBetween(String name1, String name2);

    @Query("select p from Person p where p.id between ?1 and ?2")
    List<Person> findByIdBetween(Long id1, Long id2);

    @Query("select distinct(p.programmingLanguage) from Person p")
    List<String> findAllProgrammingLanguagesDistinct();

    @Query("select count(distinct(p.programmingLanguage)) from Person p")
    Long findAllProgrammingLanguagesDistinctCount();

    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select p from Person p where p.name between 'J' and 'P'")
    List<Person> findAllByNameBetween();

    @Query("select p from Person p where p.id between 2 and 5")
    List<Person> findAllBetween();

    @Query("select new com.ivan.curso.springboot.jpa.springboot_jpa.dto.PersonDto(p.name, p.lastName) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select new Person(p.name, p.lastName) from Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findMixPerson();

    @Query("select concat(p.name, ' ', p.lastName) as fullname from Person p where p.id=?1")
    String getFullName(Long id);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("select p from Person p where p.programmingLanguage = ?1")
    List<Person> buscarByProgrammingLanguageQuery(String programmingLanguage);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);
}
