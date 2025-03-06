package org.equinox.springbootjpa.repositories;

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
}