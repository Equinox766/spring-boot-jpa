package org.equinox.springbootjpa;

import org.equinox.springbootjpa.dto.PersonDto;
import org.equinox.springbootjpa.entities.Person;
import org.equinox.springbootjpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        create();
    }

    @Transactional
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID to update:");
        Long id = scanner.nextLong();

        Optional<Person> person = personRepository.findById(id);
        person.ifPresent(person1 -> {
            System.out.println("Enter First Name:");
            String name = scanner.next();
            System.out.println("Enter Last Name:");
            String lastName = scanner.next();
            System.out.println("Enter Programming Language:");
            String programmingLenguaje = scanner.next();
            Person personUPDATED = personRepository.save(new Person(id,name,lastName,programmingLenguaje));
            System.out.println(personUPDATED);
        });
        scanner.close();
    }

    @Transactional
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Name:");
        String name = scanner.next();
        System.out.println("Enter Last Name:");
        String lastName = scanner.next();
        System.out.println("Enter Programming Language:");
        String programmingLenguaje = scanner.next();
        scanner.close();

        Person person = personRepository.save(new Person(null,name,lastName,programmingLenguaje));
//        personRepository.findById(person.getId()).orElseThrow(()->new RuntimeException("Person not found"));
        personRepository.findById(person.getId()).ifPresent(System.out::println);
    }

    @Transactional(readOnly = true)
    public void findOne() {
//        personRepository.findOne(3L).ifPresent(System.out::println);
/*        personRepository.findOneName("John").ifPresent(System.out::println);*/
//        personRepository.findOneLikeName("mAR").ifPresent(System.out::println);/**/
        personRepository.findByNameContaining("Mar").ifPresent(System.out::println);

    }

    @Transactional(readOnly = true)
    public void find() {
        //        List<Person> people = personRepository.buscarByProgrammingLanguage("Java", "Maria");
        List<Person> people = personRepository.findByProgrammingLanguageAndName("Java", "Maria");

        people.forEach(System.out::println);

        List<Object[]> peopleData = personRepository.obtenerDataPersona();
        peopleData.stream().forEach(person -> System.out.println(person[0] + " esta aprendiendo " + person[1]));
    }

    @Transactional
    public void delete() {
        personRepository.findAll().forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID to delete:");
        Long id = scanner.nextLong();
        personRepository.deleteById(id);
        scanner.close();
        personRepository.findAll().forEach(System.out::println);
    }

    @Transactional
    public void delete2() {
        personRepository.findAll().forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID to delete:");
        Long id = scanner.nextLong();
        Optional<Person> person = personRepository.findById(id);
        person.ifPresentOrElse(personRepository::delete, () ->System.out.println("No existe la persona con ese Id!"));
        scanner.close();
        personRepository.findAll().forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void personalizedQueries2() {
        System.out.println("============================ return object of person and programming language ============================");
        System.out.println("Enter ID to search:");
        List<Object[]> person = personRepository.findAllMixDataPerson();

        person.forEach(reg -> {
            System.out.println("Programming Language: " + reg[1] + ", Person: " + reg[0]);
        });

        System.out.println("=================================  Consulta y debuelve un objeto de clase entidad en una istancia personalizada  =================================");
        List<Person> persons = personRepository.findAllClassPerson();
        persons.forEach(System.out::println);


        System.out.println("\n\n=================================  Consulta y debuelve un objeto de clase entidad en una istancia dto  =================================\n\n\n");
        List<PersonDto> persons2 = personRepository.findAllDTOPerson();
        persons2.forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void personalizedQueries() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========================= return only name by id inserted =========================");
        System.out.println("Enter ID to search:");
        Long id = scanner.nextLong();
        scanner.close();
        String name = personRepository.findNameById(id);
        System.out.println(name);

        String fullname = personRepository.findFullNameByIdNameById(id);
        System.out.println(fullname);

        Object[] data = personRepository.getDataPersonaById(id);
        System.out.println(Arrays.toString(data));

        List<Object> dataPerson = personRepository.getDataPersona();
        dataPerson.forEach( result -> System.out.println("Person:"  + result ));
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesDistinct() {
        personRepository.findAllNames().forEach(System.out::println);
        personRepository.findAllUppercaseNames().forEach(System.out::println);
        System.out.println(personRepository.findNumbers());

    }

    @Transactional(readOnly = true)
    public void personalizedQueriesBetween() {
        personRepository.findAllBetween(2L, 5L).forEach(System.out::println);
        personRepository.findAllBetweenName("J", "L").forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    public void personalizedQueriesOrderBy() {
        personRepository.findAllOrderByName().forEach(System.out::println);
    }
}
