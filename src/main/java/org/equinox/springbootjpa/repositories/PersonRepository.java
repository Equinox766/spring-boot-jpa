package org.equinox.springbootjpa.repositories;

import org.equinox.springbootjpa.dto.PersonDto;
import org.equinox.springbootjpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

//    @Query("SELECT p FROM Person p WHERE p.programmingLanguage = :programmingLanguage AND p.name = :name")
    @Query("SELECT p FROM Person p WHERE p.programmingLanguage = ?1 AND p.name = ?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerDataPersona();

    @Query("SELECT p FROM Person p WHERE p.id = ?1")
    Optional<Person> findOne(Long id);

    @Query("SELECT p FROM Person p WHERE p.name = ?1")
    Optional<Object> findOneName(String name);

    @Query("SELECT p FROM Person p WHERE p.name ILIKE %?1%")
    Optional<Object> findOneLikeName(String name);

    Optional<Object> findByNameContaining(String name);

    @Query("SELECT p.name FROM Person p WHERE p.id =?1")
    String findNameById(Long id);

    @Query("SELECT CONCAT(p.name,' ', p.lastName) AS fullname  FROM Person p WHERE p.id =?1")
    String findFullNameByIdNameById(Long id);

    @Query("SELECT p FROM Person p WHERE p.id = ?1")
    Object[] getDataPersonaById(Long id);

    @Query("SELECT p FROM Person p")
    List<Object> getDataPersona();

    @Query("SELECT p, p.programmingLanguage FROM Person p")
    List<Object[]> findAllMixDataPerson();

    @Query("SELECT new Person(p.name, p.lastName) FROM Person p")
    List<Person> findAllClassPerson();

    @Query("SELECT new org.equinox.springbootjpa.dto.PersonDto(p.name, p.lastName) FROM Person p")
    List<PersonDto> findAllDTOPerson();

    @Query("SELECT DISTINCT(p.name) FROM Person p")
    List<String> findAllNames();
    @Query("SELECT DISTINCT(UPPER(p.name)) FROM Person p")
    List<String> findAllUppercaseNames();

    @Query("SELECT count(DISTINCT(p.name)) FROM Person p")
    int findNumbers();

    @Query("SELECT p FROM Person p WHERE p.id between :start and :end")
    List<Person> findAllBetween(Long start, Long end);

    @Query("SELECT p FROM Person p WHERE p.name between :start and :end")
    List<Person> findAllBetweenName(String start, String end);

    @Query("SELECT p FROM Person p ORDER BY p.name DESC")
    List<Person> findAllOrderByName();
}